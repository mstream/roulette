package io.mstream.roulette.domain.roulette.bet;


public class NumberBetType extends BetType {

    private final int number;

    public NumberBetType(int number) {
        this.number = number;
    }

    @Override
    public boolean test(Integer pocketNumber) {
        return pocketNumber.intValue() == number;
    }
}
