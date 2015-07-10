package io.mstream.roulette.domain.bet;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class NumberBetTypeTest {

	private NumberBetType instance = new NumberBetType( 2 );

	@Test
	public void shouldMatchWhenPocketNumberIsChosenNumber( ) {
		assertTrue( instance.test( 2 ) );
	}

	@Test
	public void shouldNotMatchWhenPocketNumberIsNotChosenNumber( ) {
		assertFalse( instance.test( 3 ) );
	}

	@Test
	public void TheSameBetTypesShouldBeEqual( ) {
		assertTrue( instance.equals( instance ) );
	}

	@Test
	public void DifferentBetTypesShouldBeEqual( ) {
		assertFalse( instance.equals( ( BetType ) integer -> false ) );
	}

	@Test
	public void BetTypesWithSameNumbersShouldBeEqual( ) {
		assertTrue( new NumberBetType( 10 )
				.equals( new NumberBetType( 10 ) ) );
	}

	@Test
	public void BetTypesWithDifferentNumbersShouldNotBeEqual( ) {
		assertFalse( new NumberBetType( 10 )
				.equals( new NumberBetType( 20 ) ) );
	}

}