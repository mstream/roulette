package io.mstream.roulette.events;

import io.mstream.roulette.domain.roulette.bet.BetType;

import java.math.BigDecimal;

public class PlayerWon implements Event {

    private final String playerName;
    private final BetType betType;
    private final BigDecimal prize;

    public PlayerWon(String playerName, BetType betType, BigDecimal prize) {
        this.playerName = playerName;
        this.betType = betType;
        this.prize = prize;
    }
}
