package io.mstream.roulette.domain.result;

import io.mstream.roulette.domain.bet.Bet;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class PlayerResultFactoryTest {

	private PlayerResultFactory instance =
			new PlayerResultFactory(
					bet -> bet.getAmount( ).multiply( BigDecimal.TEN ) );

	@Test
	public void shouldCreateWinningResultOnWin( ) {
		Bet bet = new Bet(
				"Foo",
				number -> true,
				new BigDecimal( 2 ) );
		//
		PlayerResult result = instance.createResult( bet, 1 );
		//
		assertNotNull( result );
		assertEquals( "Foo", result.getPlayerName( ) );
		assertEquals( Outcome.WIN, result.getOutcome( ) );
		assertEquals( new BigDecimal( 20 ), result.getWinning() );
	}

	@Test
	public void shouldCreateLosingResultOnLose( ) {
		Bet bet = new Bet(
				"Foo",
				number -> false,
				new BigDecimal( 2 ) );
		//
		PlayerResult result = instance.createResult( bet, 1 );
		//
		assertNotNull( result );
		assertEquals( "Foo", result.getPlayerName( ) );
		assertEquals( Outcome.LOSE, result.getOutcome( ) );
		assertEquals( BigDecimal.ZERO, result.getWinning() );
	}

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailOnNullBet( ) {
		//
		instance.createResult( null, 1 );
		//
	}
}