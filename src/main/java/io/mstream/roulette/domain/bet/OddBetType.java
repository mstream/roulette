package io.mstream.roulette.domain.bet;


public class OddBetType implements BetType {

    @Override
    public boolean test(Integer pocketNumber) {
        return pocketNumber.intValue() % 2 == 1;
    }

    @Override public String toString( ) {
        return "ODD";
    }

    @Override public boolean equals( Object obj ) {
        return obj.getClass().equals( OddBetType.class );
    }

    @Override public int hashCode( ) {
        return getClass().hashCode();
    }
}
