package io.mstream.roulette.domain;

import io.mstream.roulette.config.RouletteConfiguration;
import io.mstream.roulette.domain.bet.Bet;
import io.mstream.roulette.domain.bet.BetTypeFactory;
import io.mstream.roulette.domain.event.ResultsGenerated;
import io.mstream.roulette.domain.result.Outcome;
import io.mstream.roulette.domain.result.PlayerResult;
import io.mstream.roulette.domain.result.PlayerResultFactory;
import io.mstream.roulette.domain.result.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static org.junit.Assert.*;


@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = RouletteConfiguration.class )
public class RouletteIT {

	private Roulette instance;
	@Autowired
	private PlayerResultFactory playerResultFactory;
	@Autowired
	private BetTypeFactory betTypeFactory;

	@Test
	public void shouldCalculateResultsProperly( ) {
		instance = new Roulette(
				( ) -> 7,
				playerResultFactory,
				Arrays.asList(
						new Player.Builder( "P1" )
								.withTotalWin( BigDecimal.ONE )
								.withTotalBet( BigDecimal.TEN )
								.build( ),
						new Player.Builder( "P2" )
								.build( ),
						new Player.Builder( "P3" )
								.build( ),
						new Player.Builder( "P4" )
								.build( ),
						new Player.Builder( "P5" )
								.build( )
				) );
		List<Bet> bets = Arrays.asList(
				new Bet( "P1", betTypeFactory.apply( "ODD" ), BigDecimal.TEN ),
				new Bet( "P2", betTypeFactory.apply( "EVEN" ), BigDecimal.TEN ),
				new Bet( "P3", betTypeFactory.apply( "7" ), BigDecimal.TEN ),
				new Bet( "P4", betTypeFactory.apply( "3" ), BigDecimal.TEN )
		);
		ResultsObserver resultsObserver = new ResultsObserver( );
		instance.addObserver( resultsObserver );
		//
		bets
				.stream( )
				.forEach( instance::placeBet );
		instance.generateResults( );
		//
		ResultsGenerated resultsGenerated =
				resultsObserver.getResultsGenerated( );

		assertNotNull( resultsGenerated );
		Result result = resultsGenerated.getResult( );
		List<Player> players = resultsGenerated.getPlayers( );

		assertNotNull( result );
		assertNotNull( players );

		assertEquals( 7, result.getWinningNumber( ) );

		List<PlayerResult> playerResults = result.getPlayerResults( );

		assertNotNull( playerResults );
		assertEquals( 4, playerResults.size( ) );

		assertEquals( "P1", playerResults.get( 0 ).getPlayerName( ) );
		assertEquals( "P2", playerResults.get( 1 ).getPlayerName( ) );
		assertEquals( "P3", playerResults.get( 2 ).getPlayerName( ) );
		assertEquals( "P4", playerResults.get( 3 ).getPlayerName( ) );

		assertEquals(
				betTypeFactory.apply( "ODD" ),
				playerResults.get( 0 ).getBetType( ) );
		assertEquals( betTypeFactory.apply( "EVEN" ),
				playerResults.get( 1 ).getBetType( ) );
		assertEquals( betTypeFactory.apply( "7" ),
				playerResults.get( 2 ).getBetType( ) );
		assertEquals( betTypeFactory.apply( "3" ),
				playerResults.get( 3 ).getBetType( ) );

		assertEquals( Outcome.WIN, playerResults.get( 0 ).getOutcome( ) );
		assertEquals( Outcome.LOSE, playerResults.get( 1 ).getOutcome( ) );
		assertEquals( Outcome.WIN, playerResults.get( 2 ).getOutcome( ) );
		assertEquals( Outcome.LOSE, playerResults.get( 3 ).getOutcome( ) );

		assertEquals(
				20,
				playerResults.get( 0 ).getWinning( ).doubleValue( ),
				0 );
		assertEquals(
				0,
				playerResults.get( 1 ).getWinning( ).doubleValue( ),
				0 );
		assertEquals(
				360,
				playerResults.get( 2 ).getWinning( ).doubleValue( ),
				0 );
		assertEquals(
				0,
				playerResults.get( 3 ).getWinning( ).doubleValue( ),
				0 );

		assertEquals( "P1", players.get( 0 ).getName( ) );
		assertEquals( "P2", players.get( 1 ).getName( ) );
		assertEquals( "P3", players.get( 2 ).getName( ) );
		assertEquals( "P4", players.get( 3 ).getName( ) );
		assertEquals( "P5", players.get( 4 ).getName( ) );

		assertEquals( 21, players.get( 0 ).getTotalWin( ).doubleValue( ), 0 );
		assertEquals( 0, players.get( 1 ).getTotalWin( ).doubleValue( ), 0 );
		assertEquals( 360, players.get( 2 ).getTotalWin( ).doubleValue( ), 0 );
		assertEquals( 0, players.get( 3 ).getTotalWin( ).doubleValue( ), 0 );
		assertEquals( 0, players.get( 4 ).getTotalWin( ).doubleValue( ), 0 );

		assertEquals( 20, players.get( 0 ).getTotalBet( ).doubleValue( ), 0 );
		assertEquals( 10, players.get( 1 ).getTotalBet( ).doubleValue( ), 0 );
		assertEquals( 10, players.get( 2 ).getTotalBet( ).doubleValue( ), 0 );
		assertEquals( 10, players.get( 3 ).getTotalBet( ).doubleValue( ), 0 );
		assertEquals( 0, players.get( 4 ).getTotalBet( ).doubleValue( ), 0 );
	}

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailOnNullBet( ) {
		instance = new Roulette(
				( ) -> 7,
				playerResultFactory,
				Arrays.asList( ) );
		ResultsObserver resultsObserver = new ResultsObserver( );
		instance.addObserver( resultsObserver );
		//
		instance.placeBet( null );
		//
	}

	@Test
	public void shouldNotAcceptBetFromUnregisteredUser( ) {
		instance = new Roulette(
				( ) -> 7,
				playerResultFactory,
				Arrays.asList(
						new Player.Builder( "P1" )
								.build( )
				) );
		List<Bet> bets = Arrays.asList(
				new Bet( "PX", betTypeFactory.apply( "ODD" ), BigDecimal.TEN )
		);
		ResultsObserver resultsObserver = new ResultsObserver( );
		instance.addObserver( resultsObserver );
		//
		bets
				.stream( )
				.forEach( instance::placeBet );
		instance.generateResults( );
		//
		ResultsGenerated resultsGenerated =
				resultsObserver.getResultsGenerated( );

		assertNotNull( resultsGenerated );
		Result result = resultsGenerated.getResult( );
		assertNotNull( result );
		assertNotNull( result.getPlayerResults( ) );
		assertTrue( result.getPlayerResults( ).isEmpty( ) );
	}

	private static class ResultsObserver implements Observer {

		private ResultsGenerated resultsGenerated;

		@Override public void update( Observable o, Object arg ) {
			resultsGenerated = ( ResultsGenerated ) arg;
		}

		public ResultsGenerated getResultsGenerated( ) {
			return resultsGenerated;
		}
	}
}