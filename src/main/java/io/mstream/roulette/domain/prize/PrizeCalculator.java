package io.mstream.roulette.domain.prize;

import io.mstream.roulette.domain.prize.rule.PrizeCalculationRule;
import io.mstream.roulette.domain.bet.Bet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;


@Component
public class PrizeCalculator implements Function<Bet, BigDecimal> {

	private final Map<String, PrizeCalculationRule> rules;

	@Autowired
	public PrizeCalculator(
			@Value( "#{priceCalculationRules}" )
			Map<String, PrizeCalculationRule> rules ) {
		this.rules = Collections.unmodifiableMap( rules );
	}

	@Override public BigDecimal apply( Bet bet ) {
		String betClassName = bet.getType( ).getClass( ).getName( );
		PrizeCalculationRule rule = rules.get( betClassName );
		if ( rule == null ) {
			throw new IllegalArgumentException( "no rule for given bet type: "
					+ betClassName );
		}
		return rule.apply( bet.getAmount( ) );
	}
}
