package io.mstream.roulette.domain.roulette.bet;


public class OddBetType extends BetType {

    @Override
    public boolean test(Integer pocketNumber) {
        return pocketNumber.intValue() % 2 == 1;
    }

    @Override public String toString( ) {
        return "ODD";
    }
}
