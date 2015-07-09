package io.mstream.roulette.parsing;

import io.mstream.roulette.domain.bet.Bet;
import io.mstream.roulette.domain.bet.BetTypeFactory;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class BetLineStringParserIntegrationTest {

	private BetTypeFactory betTypeFactory = new BetTypeFactory( );
	private BetLineStringParser instance = new BetLineStringParser( betTypeFactory );

	@Test
	public void shouldCreateBetOfEvenTypes( ) {
		String betLine = "John EVEN 2.0";
		//
		Bet parsedBet = instance.apply( betLine );
		//
		assertNotNull( parsedBet );
		assertEquals( "John", parsedBet.getPlayerName( ) );
		assertNotNull( parsedBet.getType( ) );
		assertEquals( betTypeFactory.fromString( "EVEN" ), parsedBet.getType( ) );
		assertNotNull( parsedBet.getAmount( ) );
		assertEquals( new BigDecimal( 2.0 ).doubleValue( ), parsedBet.getAmount( ).doubleValue( ), 0 );
	}

	@Test
	public void shouldCreateBetOfOddTypes( ) {
		String betLine = "John ODD 2.0";
		//
		Bet parsedBet = instance.apply( betLine );
		//
		assertNotNull( parsedBet );
		assertEquals( "John", parsedBet.getPlayerName( ) );
		assertNotNull( parsedBet.getType( ) );
		assertEquals( betTypeFactory.fromString( "ODD" ), parsedBet.getType( ) );
		assertNotNull( parsedBet.getAmount( ) );
		assertEquals( new BigDecimal( 2.0 ).doubleValue( ), parsedBet.getAmount( ).doubleValue( ), 0 );
	}

	@Test
	public void shouldCreateBetOfNumberTypes( ) {
		String betLine = "John 10 2.0";
		//
		Bet parsedBet = instance.apply( betLine );
		//
		assertNotNull( parsedBet );
		assertEquals( "John", parsedBet.getPlayerName( ) );
		assertNotNull( parsedBet.getType( ) );
		assertEquals( betTypeFactory.fromString( "10" ), parsedBet.getType( ) );
		assertNotNull( parsedBet.getAmount( ) );
		assertEquals( new BigDecimal( 2.0 ).doubleValue(), parsedBet.getAmount( ).doubleValue(), 0 );
	}
}