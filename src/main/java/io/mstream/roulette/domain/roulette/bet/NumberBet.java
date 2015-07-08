package io.mstream.roulette.domain.roulette.bet;


public class NumberBet extends Bet {

    private final int number;

    public NumberBet(int number) {
        this.number = number;
    }

    @Override
    public boolean test(Integer pocketNumber) {
        return pocketNumber.intValue() == number;
    }
}
