package io.mstream.roulette.domain.random;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RangeNumberGeneratorTest {

	private RangeNumberGenerator instance;

	@Test
	public void shouldGenerateNumbersInGivenRange_minValue( ) {
		instance = new RangeNumberGenerator(
				bound -> 0,
				50,
				100 );
		//
		int generatedNumber = instance.get( );
		//
		assertEquals( 50, generatedNumber );
	}

	@Test
	public void shouldGenerateNumbersInGivenRange_maxValue( ) {
		instance = new RangeNumberGenerator(
				bound -> bound - 1,
				50,
				100 );
		//
		int generatedNumber = instance.get( );
		//
		assertEquals( 100, generatedNumber );
	}

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailOnConstructingWithMinNumberGreaterThanMaxNumber( ) {
		//
		instance = new RangeNumberGenerator(
				bound -> 0,
				100,
				50 );        //
	}
}