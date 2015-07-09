package io.mstream.roulette.parsing;

import io.mstream.roulette.domain.roulette.bet.Bet;
import io.mstream.roulette.domain.roulette.bet.BetTypeFactory;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BetLineStringParser implements StringParser<Bet> {

    private final Pattern linePattern = Pattern.compile(
            "(?<playerName>\\w+)(\\s+(?<bet>\\w+|d+))(\\s+(?<amount>\\d+(\\.\\d+)?))\\s*");

    private final BetTypeFactory betTypeFactory;

    public BetLineStringParser(BetTypeFactory betTypeFactory) {
        this.betTypeFactory = betTypeFactory;
    }

    @Override
    public Bet apply(String betLine) {
        if (betLine == null) {
            throw new IllegalArgumentException();
        }
        Matcher matcher = linePattern.matcher(betLine);
        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }
        String playerName = matcher.group("playerName");
        String betStr = matcher.group("bet");
        String amountStr = matcher.group("amount");

        return new Bet(playerName, betTypeFactory.fromString(betStr), new BigDecimal(amountStr));
    }

}
