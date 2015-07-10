package io.mstream.roulette.domain;

import io.mstream.roulette.domain.bet.Bet;
import io.mstream.roulette.domain.event.ResultsGenerated;
import io.mstream.roulette.domain.random.NumbersGenerator;
import io.mstream.roulette.domain.result.Outcome;
import io.mstream.roulette.domain.result.PlayerResult;
import io.mstream.roulette.domain.result.PlayerResultFactory;
import io.mstream.roulette.domain.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class Roulette extends Observable {

	private final NumbersGenerator numbersGenerator;
	private final PlayerResultFactory playerResultFactory;

	private final Map<String, Player> players;
	private final Map<Player, Bet> bets = new LinkedHashMap<>( );

	private final Lock resultGeneratorLock = new ReentrantLock( );

	@Autowired
	public Roulette(
			NumbersGenerator numbersGenerator,
			PlayerResultFactory playerResultFactory,
			@Value( "#{players}" )
			List<Player> players ) {
		this.numbersGenerator = numbersGenerator;
		this.playerResultFactory = playerResultFactory;
		this.players = players
				.stream( )
				.collect( Collectors.toMap(
								Player::getName,
								Function.<Player>identity( ),
								( k1, k2 ) -> k2,
								LinkedHashMap::new )
				);
	}

	public void placeBet( Bet bet ) {
		if ( bet == null ) {
			throw new IllegalArgumentException( "bet cannot be null" );
		}
		String playerName = bet.getPlayerName( );
		Player player = players.get( playerName );
		if ( player == null ) {
			System.err.print( "no such player: " + playerName );
			return;
		}
		resultGeneratorLock.lock( );
		bets.put( player, bet );
		resultGeneratorLock.unlock( );
	}

	@Scheduled(
			initialDelayString = "${roulette.rollRateInMilliseconds}",
			fixedRateString = "${roulette.rollRateInMilliseconds}" )
	public void generateResults( ) {
		int winningNumber = numbersGenerator.get( );
		resultGeneratorLock.lock( );
		List<PlayerResult> playersResults = calculateResults( winningNumber );
		updatePlayersStatus( playersResults );
		bets.clear( );
		Result result = new Result( winningNumber, playersResults );
		setChanged( );
		notifyObservers( new ResultsGenerated( result, players.values( ) ) );
		resultGeneratorLock.unlock( );
	}

	private List<PlayerResult> calculateResults( int winningNumber ) {
		return bets.values( )
				.stream( )
				.map( bet -> playerResultFactory.createResult(
								bet,
								winningNumber )
				)
				.collect( Collectors.toList( ) );
	}

	private void updatePlayersStatus( List<PlayerResult> playerResults ) {
		playerResults
				.stream( )
				.forEach( result -> {
							String playerName = result.getPlayerName( );
							Player player = players.get( playerName );
							BigDecimal currentTotalBet = player.getTotalBet( );
							BigDecimal currentTotalWin = player.getTotalWin( );
							Bet bet = bets.get( player );
							player = new Player.Builder( playerName )
									.withTotalBet( currentTotalBet
											.add( bet.getAmount( ) ) )
									.withTotalWin( result.getOutcome( )
											== Outcome.WIN ?
											currentTotalWin.add( result
													.getWinning( ) ) :
											currentTotalWin )
									.build( );
							players.put( playerName, player );
						}
				);
	}

}
