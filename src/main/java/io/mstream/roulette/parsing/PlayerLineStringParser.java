package io.mstream.roulette.parsing;

import io.mstream.roulette.command.RegisterPlayer;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerLineStringParser extends StringParser<RegisterPlayer> {

    private Pattern linePattern = Pattern.compile(
            "(?<playerName>\\w+)(\\s*,\\s*(?<totalWin>\\d+(\\.\\d+)?))?(\\s*,\\s*(?<totalBet>\\d+(\\.\\d+)?))?");

    @Override
    public RegisterPlayer apply(String playerLine) {
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
        RegisterPlayer.Builder registerPlayerBuilder =
                new RegisterPlayer.Builder(playerName);
        if (totalWinStr != null) {
            registerPlayerBuilder.withTotalWin(new BigDecimal(totalWinStr));
        }
        if (totalBetStr != null) {
            registerPlayerBuilder.withTotalBet(new BigDecimal(totalBetStr));
        }
        return registerPlayerBuilder.build();
    }

    @Override
    public <V> Function<V, RegisterPlayer> compose(Function<? super V, ? extends String> before) {
        return null;
    }

    @Override
    public <V> Function<String, V> andThen(Function<? super RegisterPlayer, ? extends V> after) {
        return null;
    }
}
