package io.mstream.roulette.domain.prize;

import io.mstream.roulette.domain.bet.Bet;
import io.mstream.roulette.domain.bet.BetType;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.Assert.assertEquals;


public class PrizeCalculatorTest {

	private static class MappedTestBetType extends BetType {

		@Override public boolean test( Integer integer ) {
			return false;
		}
	}

	private static class NotMappedTestBetType extends BetType {

		@Override public boolean test( Integer integer ) {
			return false;
		}
	}

	private PrizeCalculator instance = new PrizeCalculator(
			Collections.singletonMap(
					MappedTestBetType.class.getName( ),
					value -> BigDecimal.TEN.multiply( value )
			) );

	@Test
	public void shouldApplyMappedRule( ) {
		BigDecimal betAmount = BigDecimal.valueOf( 2 );
		BigDecimal expectedPrize = BigDecimal.valueOf( 20 );
		//
		BigDecimal prize = instance.apply(
				new Bet(
						"Foo",
						new MappedTestBetType( ),
						betAmount
				)
		);
		//
		assertEquals(
				expectedPrize.doubleValue( ),
				prize.doubleValue( ),
				0 );
	}

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailWhenNoMappedRule( ) {
		BigDecimal betAmount = BigDecimal.valueOf( 2 );
		//
		BigDecimal prize = instance.apply(
				new Bet(
						"Foo",
						new NotMappedTestBetType( ),
						betAmount
				)
		);
		//
	}
}