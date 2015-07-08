package io.mstream.roulette.domain.roulette;


import io.mstream.roulette.domain.roulette.bet.BetType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;


public class PrizeCalculator {

    private final Map<BetType, UnaryOperator<BigDecimal>> prizeRules = new HashMap<>();

    public BigDecimal prize(BigDecimal betAmount, BetType betTypeType) {
        return prizeRules.get(betTypeType).apply(betAmount);
    }
}
