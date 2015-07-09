package io.mstream.roulette.input.parsing.player;

import io.mstream.roulette.domain.Player;
import io.mstream.roulette.input.parsing.StringParser;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class PlayerLineStringParser implements StringParser<Player> {

    private Pattern linePattern = Pattern.compile(
            "(?<playerName>\\w+)(\\s*,\\s*(?<totalWin>\\d+(\\.\\d+)?))?(\\s*,\\s*(?<totalBet>\\d+(\\.\\d+)?))?\\s*");

    @Override
    public Player apply(String playerLine) {
        if (playerLine == null) {
            throw new IllegalArgumentException();
        }
        Matcher matcher = linePattern.matcher(playerLine);
        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }
        String playerName = matcher.group("playerName");
        String totalWinStr = matcher.group("totalWin");
        String totalBetStr = matcher.group("totalBet");
        Player.Builder registerPlayerBuilder =
                new Player.Builder(playerName);
        if (totalWinStr != null) {
            registerPlayerBuilder.withTotalWin(new BigDecimal(totalWinStr));
        }
        if (totalBetStr != null) {
            registerPlayerBuilder.withTotalBet(new BigDecimal(totalBetStr));
        }
        return registerPlayerBuilder.build();
    }

}
