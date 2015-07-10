package io.mstream.roulette.domain.bet;

import org.junit.Test;

import static org.junit.Assert.*;


public class BetTypeFactoryTest {

	private BetTypeFactory instance = new BetTypeFactory( );

	@Test
	public void shouldCreateEvenBetTypeFromString( ) {
		String betTypeStr = "EVEN";
		//
		BetType betType = instance.apply( betTypeStr );
		//
		assertNotNull( betType );
		assertEquals( EvenBetType.class, betType.getClass( ) );
	}

	@Test
	public void shouldCreateOddBetTypeFromString( ) {
		String betTypeStr = "ODD";
		//
		BetType betType = instance.apply( betTypeStr );
		//
		assertNotNull( betType );
		assertEquals( OddBetType.class, betType.getClass( ) );
	}

	@Test
	public void shouldCreateNumberBetTypeFromString( ) {
		String betTypeStr = "5";
		//
		BetType betType = instance.apply( betTypeStr );
		//
		assertNotNull( betType );
		assertEquals( NumberBetType.class, betType.getClass( ) );
		assertTrue( betType.test( 5 ) );
	}

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailOnNumberBetTypeOfZero( ) {
		String betTypeStr = "0";
		//
		instance.apply( betTypeStr );
		//
	}

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailOnNumberBetTypeOutOfRange( ) {
		String betTypeStr = "50";
		//
		instance.apply( betTypeStr );
		//
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailOnUnknownBetTypeString( ) {
		String betTypeStr = "SOME_UNKNOWN_ONE";
		//
		instance.apply( betTypeStr );
		//
	}

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailOnNullValue( ) {
		//
		instance.apply( null );
		//
	}
}