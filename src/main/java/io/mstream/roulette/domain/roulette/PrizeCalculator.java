package io.mstream.roulette.domain.roulette;


import io.mstream.roulette.domain.roulette.bet.Bet;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;


public class PrizeCalculator {

    private final Map<Bet, UnaryOperator<BigDecimal>> prizeRules = new HashMap<>();

    public BigDecimal prize(BigDecimal betAmount, Bet betType) {
        return prizeRules.get(betType).apply(betAmount);
    }
}
