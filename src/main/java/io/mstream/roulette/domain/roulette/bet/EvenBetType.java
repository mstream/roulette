package io.mstream.roulette.domain.roulette.bet;


public class EvenBetType extends BetType {

    @Override
    public boolean test(Integer pocketNumber) {
        return pocketNumber.intValue() % 2 == 0;
    }

    @Override public String toString( ) {
        return "EVEN";
    }
}
