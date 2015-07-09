package io.mstream.roulette.parsing;

import io.mstream.roulette.domain.bet.Bet;
import io.mstream.roulette.domain.bet.BetTypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class BetLineStringParser implements StringParser<Optional<Bet>> {

    private final Pattern linePattern = Pattern.compile(
            "(?<playerName>\\w+)(\\s+(?<bet>\\w+|d+))(\\s+(?<amount>\\d+(\\.\\d+)?))\\s*");

    private final BetTypeFactory betTypeFactory;

    @Autowired
    public BetLineStringParser(BetTypeFactory betTypeFactory) {
        this.betTypeFactory = betTypeFactory;
    }

    @Override
    public Optional<Bet> apply( String betLine ) {
        if (betLine == null) {
            throw new IllegalArgumentException();
        }
        Matcher matcher = linePattern.matcher(betLine);
        if (!matcher.matches()) {
            return Optional.empty( );
        }
        String playerName = matcher.group("playerName");
        String betStr = matcher.group("bet");
        String amountStr = matcher.group("amount");

        return Optional.of(
                new Bet(
                        playerName,
                        betTypeFactory.fromString( betStr ),
                        new BigDecimal( amountStr )
                )
        );
    }

}
