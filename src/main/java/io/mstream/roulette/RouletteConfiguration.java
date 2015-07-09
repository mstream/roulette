package io.mstream.roulette;

import io.mstream.roulette.domain.roulette.NumbersGenerator;
import io.mstream.roulette.domain.roulette.RangeNumberGenerator;
import io.mstream.roulette.domain.roulette.bet.EvenBetType;
import io.mstream.roulette.domain.roulette.bet.NumberBetType;
import io.mstream.roulette.domain.roulette.bet.OddBetType;
import io.mstream.roulette.domain.roulette.prize.rule.PrizeCalculationRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class RouletteConfiguration {

	@Bean
	public NumbersGenerator numbersGenerator( ) {
		return new RangeNumberGenerator( 0, 36 );
	}

	@Bean(name = "priceCalculationRules")
	Map<String, PrizeCalculationRule> priceCalculationRules( ) {
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
}
