package io.mstream.roulette.config;

import io.mstream.roulette.domain.NumbersGenerator;
import io.mstream.roulette.domain.Player;
import io.mstream.roulette.domain.RangeNumberGenerator;
import io.mstream.roulette.domain.bet.EvenBetType;
import io.mstream.roulette.domain.bet.NumberBetType;
import io.mstream.roulette.domain.bet.OddBetType;
import io.mstream.roulette.domain.prize.rule.PrizeCalculationRule;
import io.mstream.roulette.input.parsing.player.PlayersLinesParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@PropertySource( "classpath:application.properties" )
public class RouletteConfiguration {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer( ) {
		return new PropertySourcesPlaceholderConfigurer( );
	}

	@Bean
	public NumbersGenerator numbersGenerator( ) {
		return new RangeNumberGenerator( 0, 36 );
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
	public List<Player> players( PlayersLinesParser playersParser ) {
		return playersParser.apply( "Foo\nBar\nBiz" );
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
