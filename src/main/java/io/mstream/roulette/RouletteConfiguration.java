package io.mstream.roulette;

import io.mstream.roulette.domain.NumbersGenerator;
import io.mstream.roulette.domain.Player;
import io.mstream.roulette.domain.RangeNumberGenerator;
import io.mstream.roulette.domain.bet.EvenBetType;
import io.mstream.roulette.domain.bet.NumberBetType;
import io.mstream.roulette.domain.bet.OddBetType;
import io.mstream.roulette.domain.prize.rule.PrizeCalculationRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
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
	public List<Player> players( ) {
		return Arrays.asList(
				new Player.Builder( "Foo" ).build( ),
				new Player.Builder( "Bar" ).build( ),
				new Player.Builder( "Biz" ).build( )
		);
	}

	@Bean
	public InputStream inputStream( ) {
		return System.in;
	}

}
