package io.mstream.roulette.domain.roulette.bet;


public class EvenBet extends Bet {

    @Override
    public boolean test(Integer pocketNumber) {
        return pocketNumber.intValue() % 2 == 0;
    }
}
