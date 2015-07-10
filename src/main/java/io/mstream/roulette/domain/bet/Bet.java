package io.mstream.roulette.domain.bet;

import java.math.BigDecimal;

public class Bet {

    private final String playerName;
    private final BetType type;
    private final BigDecimal amount;

    public Bet(String playerName, BetType type, BigDecimal amount) {
        this.playerName = playerName;
        this.type = type;
        if (BigDecimal.ZERO.compareTo( amount ) != -1) {
            throw new IllegalArgumentException(
                    "bet amount should be a positive number" );
        }
        this.amount = amount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public BetType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
