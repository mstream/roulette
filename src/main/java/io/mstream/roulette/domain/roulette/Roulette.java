package io.mstream.roulette.domain.roulette;


import io.mstream.roulette.domain.roulette.bet.Bet;

import java.util.Map;

public class Roulette {

    private final NumbersGenerator numbersGenerator;
    private Map<Player, Bet> bets;

    public Roulette(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

}
