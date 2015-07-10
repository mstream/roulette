package io.mstream.roulette.input.parsing;

import io.mstream.roulette.domain.bet.Bet;
import io.mstream.roulette.domain.bet.BetType;
import io.mstream.roulette.input.parsing.bet.BetLineStringParser;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;


public class BetLineStringParserTest {

	private final BetType betType = integer -> false;

	private BetLineStringParser instance =
			new BetLineStringParser( str -> betType );

	@Test
	public void shouldCreateBets( ) {
		String betLine = "John XXX 2.0";
		//
		Optional<Bet> parsedBetOpt = instance.apply( betLine );
		//
		assertNotNull( parsedBetOpt );
		assertTrue( parsedBetOpt.isPresent( ) );
		Bet parsedBet = parsedBetOpt.get( );
		assertEquals( "John", parsedBet.getPlayerName( ) );
		assertNotNull( parsedBet.getType( ) );
		assertEquals( betType, parsedBet.getType( ) );
		assertNotNull( parsedBet.getAmount( ) );
		assertEquals( new BigDecimal( 2.0 ).doubleValue( ),
				parsedBet.getAmount( ).doubleValue( ), 0 );
	}

	@Test
	public void shouldReturnNoBetIfInputFormatIsIncorrect( ) {
		String betLine = "John_XXX_2.0";
		//
		Optional<Bet> parsedBetOpt = instance.apply( betLine );
		//
		assertNotNull( parsedBetOpt );
		assertFalse( parsedBetOpt.isPresent( ) );
	}

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailOnNullValue( ) {
		//
		instance.apply( null );
		//
	}
}