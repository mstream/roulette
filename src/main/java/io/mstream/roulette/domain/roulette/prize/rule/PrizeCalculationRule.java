package io.mstream.roulette.domain.roulette.prize.rule;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;


public interface PrizeCalculationRule extends UnaryOperator<BigDecimal> {
}
