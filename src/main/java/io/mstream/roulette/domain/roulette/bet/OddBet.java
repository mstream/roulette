package io.mstream.roulette.domain.roulette.bet;


public class OddBet extends Bet {

    @Override
    public boolean test(Integer pocketNumber) {
        return pocketNumber.intValue() % 2 == 1;
    }
}
