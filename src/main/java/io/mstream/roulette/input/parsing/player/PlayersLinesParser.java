package io.mstream.roulette.input.parsing.player;

import io.mstream.roulette.domain.Player;
import io.mstream.roulette.input.parsing.StringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class PlayersLinesParser implements StringParser<List<Player>> {

    private final StringParser<Player> playerLineParser;

    @Autowired
    public PlayersLinesParser( StringParser<Player> playerLineParser ) {
        this.playerLineParser = playerLineParser;
    }

    @Override
    public List<Player> apply(String playersLines) {
        try (BufferedReader bufferedReader = new BufferedReader(new StringReader(playersLines))) {
            return bufferedReader
                    .lines()
                    .map(playerLineParser::apply)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("could not parse players", e);
        }
    }

}
