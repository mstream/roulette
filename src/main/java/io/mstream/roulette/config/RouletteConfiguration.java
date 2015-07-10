package io.mstream.roulette.config;

import io.mstream.roulette.domain.Player;
import io.mstream.roulette.domain.bet.EvenBetType;
import io.mstream.roulette.domain.bet.NumberBetType;
import io.mstream.roulette.domain.bet.OddBetType;
import io.mstream.roulette.domain.prize.rule.PrizeCalculationRule;
import io.mstream.roulette.domain.random.NumbersGenerator;
import io.mstream.roulette.domain.random.RandomNumbersGenerator;
import io.mstream.roulette.domain.random.RangeNumberGenerator;
import io.mstream.roulette.domain.random.SecureNumbersGenerator;
import io.mstream.roulette.input.PlayersListReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@ComponentScan( basePackages = "io.mstream.roulette" )
@PropertySource( "classpath:application.properties" )
public class RouletteConfiguration {

	private static final Logger LOGGER =
			LoggerFactory.getLogger( RouletteConfiguration.class );

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer( ) {
		return new PropertySourcesPlaceholderConfigurer( );
	}

	@Bean
	public RandomNumbersGenerator random( ) {
		return new SecureNumbersGenerator( );
	}

	@Bean
	public NumbersGenerator numbersGenerator( RandomNumbersGenerator random ) {
		return new RangeNumberGenerator( random, 0, 36 );
	}

	@Bean( name = "priceCalculationRules" )
	public Map<String, PrizeCalculationRule> priceCalculationRules( ) {
		Map<String, PrizeCalculationRule> rules = new HashMap<>( );

		BigDecimal thirtySix = BigDecimal.valueOf( 36 );
		BigDecimal two = BigDecimal.valueOf( 2 );

		rules.put(
				NumberBetType.class.getName( ),
				thirtySix::multiply );
		rules.put(
				EvenBetType.class.getName( ),
				two::multiply );
		rules.put(
				OddBetType.class.getName( ),
				two::multiply );

		return rules;
	}

	@Bean( name = "players" )
	public List<Player> players(
			PlayersListReader playersListReader,
			@Value( "${players.fileName}" )
			String fileName )
			throws URISyntaxException {
		try {
			return playersListReader.getPlayers(
					Paths.get(
							ClassLoader.getSystemResource( fileName ).toURI( ) ) );
		} catch ( Exception e ) {
			LOGGER.error( "error while loading players", e );
		}
		return Collections.emptyList( );
	}

	@Bean
	public InputStream inputStream( ) {
		return System.in;
	}

	@Bean
	public PrintStream printStream( ) {
		return System.out;
	}

}
