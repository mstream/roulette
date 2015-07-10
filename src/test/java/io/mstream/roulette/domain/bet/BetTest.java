package io.mstream.roulette.domain.bet;

import org.junit.Test;

import java.math.BigDecimal;


public class BetTest {

	private BetTypeFactory betTypeFactory = new BetTypeFactory( );

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailOnNonPositiveAmount_zero( ) {
		//
		new Bet(
				"Foo",
				betTypeFactory.apply( "ODD" ),
				BigDecimal.ZERO );
		//
	}

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailOnNonPositiveAmount_negative( ) {
		//
		new Bet(
				"Foo",
				betTypeFactory.apply( "ODD" ),
				BigDecimal.valueOf( -1 ) );
		//
	}
}