package io.mstream.roulette.input.parsing;

import io.mstream.roulette.config.RouletteConfiguration;
import io.mstream.roulette.domain.Roulette;
import io.mstream.roulette.domain.bet.Bet;
import io.mstream.roulette.domain.bet.BetType;
import io.mstream.roulette.domain.bet.BetTypeFactory;
import io.mstream.roulette.input.parsing.bet.BetLineStringParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(classes = RouletteConfiguration.class)
public class BetLineStringParserIT {

	private BetTypeFactory betTypeFactory = new BetTypeFactory();

	@Autowired
	private BetLineStringParser instance;

	@Test
	public void shouldCreateBetOfEvenTypes( ) {
		String betLine = "John EVEN 2.0";
		//
		Optional<Bet> parsedBetOpt = instance.apply( betLine );
		//
		assertNotNull( parsedBetOpt );
		assertTrue( parsedBetOpt.isPresent( ) );
		Bet parsedBet = parsedBetOpt.get( );
		assertEquals( "John", parsedBet.getPlayerName( ) );
		assertNotNull( parsedBet.getType( ) );
		assertEquals( betTypeFactory.apply( "EVEN" ),
				parsedBet.getType( ) );
		assertNotNull( parsedBet.getAmount( ) );
		assertEquals( new BigDecimal( 2.0 ).doubleValue( ),
				parsedBet.getAmount( ).doubleValue( ), 0 );
	}

	@Test
	public void shouldCreateBetOfOddTypes( ) {
		String betLine = "Emma ODD 3.0";
		//
		Optional<Bet> parsedBetOpt = instance.apply( betLine );
		//
		assertNotNull( parsedBetOpt );
		assertTrue( parsedBetOpt.isPresent( ) );
		Bet parsedBet = parsedBetOpt.get( );
		assertEquals( "Emma", parsedBet.getPlayerName( ) );
		assertNotNull( parsedBet.getType( ) );
		assertEquals( betTypeFactory.apply( "ODD" ),
				parsedBet.getType( ) );
		assertNotNull( parsedBet.getAmount( ) );
		assertEquals( new BigDecimal( 3.0 ).doubleValue( ),
				parsedBet.getAmount( ).doubleValue( ), 0 );
	}

	@Test
	public void shouldCreateBetOfNumberTypes( ) {
		String betLine = "Tom 10 4.0";
		//
		Optional<Bet> parsedBetOpt = instance.apply( betLine );
		//
		assertNotNull( parsedBetOpt );
		assertTrue( parsedBetOpt.isPresent( ) );
		Bet parsedBet = parsedBetOpt.get( );
		assertEquals( "Tom", parsedBet.getPlayerName( ) );
		assertNotNull( parsedBet.getType( ) );
		assertEquals( betTypeFactory.apply( "10" ), parsedBet.getType( ) );
		assertNotNull( parsedBet.getAmount( ) );
		assertEquals( new BigDecimal( 4.0 ).doubleValue( ),
				parsedBet.getAmount( ).doubleValue( ), 0 );
	}

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailOnNullValue( ) {
		//
		instance.apply( null );
		//
	}
}