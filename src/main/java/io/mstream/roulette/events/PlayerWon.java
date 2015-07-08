package io.mstream.roulette.events;

import io.mstream.roulette.domain.roulette.bet.Bet;

import java.math.BigDecimal;

public class PlayerWon {

    private final String playerName;
    private final Bet bet;
    private final BigDecimal prize;

    public PlayerWon(String playerName, Bet bet, BigDecimal prize) {
        this.playerName = playerName;
        this.bet = bet;
        this.prize = prize;
    }
}
