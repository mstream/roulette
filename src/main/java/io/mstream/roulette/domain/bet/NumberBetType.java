package io.mstream.roulette.domain.bet;


public class NumberBetType implements BetType {

    private final int number;

    public NumberBetType(int number) {
        this.number = number;
    }

    @Override
    public boolean test(Integer pocketNumber) {
        return pocketNumber.intValue() == number;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass( ) != o.getClass( ) )
            return false;

        NumberBetType that = ( NumberBetType ) o;

        if ( number != that.number )
            return false;

        return true;
    }

    @Override
    public int hashCode( ) {
        return number;
    }

    @Override public String toString( ) {
        return String.valueOf( number );
    }
}
