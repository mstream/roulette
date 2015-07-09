package io.mstream.roulette.domain.prize.rule;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;


public interface PrizeCalculationRule extends UnaryOperator<BigDecimal> {
}
