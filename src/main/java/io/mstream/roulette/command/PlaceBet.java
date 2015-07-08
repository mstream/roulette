package io.mstream.roulette.command;

import java.math.BigDecimal;

public class PlaceBet {

    private final String playerName;
    private final BigDecimal amount;

    public PlaceBet(String playerName, BigDecimal amount) {
        this.playerName = playerName;
        this.amount = amount;
    }
}
