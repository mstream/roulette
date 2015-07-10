package io.mstream.roulette.domain.bet;


public class EvenBetType extends BetType {

    @Override
    public boolean test(Integer pocketNumber) {
        return pocketNumber.intValue() % 2 == 0;
    }

    @Override public String toString( ) {
        return "EVEN";
    }

    @Override public boolean equals( Object obj ) {
        return obj instanceof EvenBetType;
    }
}
