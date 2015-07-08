package io.mstream.roulette.parsing;

import io.mstream.roulette.command.RegisterPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersDocumentParser extends StringParser<List<RegisterPlayer>> {

    private final PlayerLineStringParser playerLineParser;

    public PlayersDocumentParser(PlayerLineStringParser playerLineParser) {
        this.playerLineParser = playerLineParser;
    }

    @Override
    public List<RegisterPlayer> apply(String playersDocument) {
        try (BufferedReader bufferedReader = new BufferedReader(new StringReader(playersDocument))) {
            return bufferedReader
                    .lines()
                    .map(playerLineParser::apply)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException("", e);
        }
    }

}
