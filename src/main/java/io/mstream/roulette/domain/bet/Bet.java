package io.mstream.roulette.domain.bet;

import java.math.BigDecimal;

public class Bet {

    private final String playerName;
    private final BetType type;
    private final BigDecimal amount;

    public Bet(String playerName, BetType type, BigDecimal amount) {
        this.playerName = playerName;
        this.type = type;
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
