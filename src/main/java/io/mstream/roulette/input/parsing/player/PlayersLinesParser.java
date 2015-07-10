package io.mstream.roulette.input.parsing.player;

import io.mstream.roulette.domain.Player;
import io.mstream.roulette.input.parsing.StringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class PlayersLinesParser implements InputStreamParser<List<Player>> {

	private final StringParser<Player> playerLineParser;

	@Autowired
	public PlayersLinesParser( StringParser<Player> playerLineParser ) {
		this.playerLineParser = playerLineParser;
	}

	@Override
	public List<Player> apply( InputStream inputStream ) {
		try (BufferedReader bufferedReader =
				new BufferedReader( new InputStreamReader( inputStream ) )) {
			return bufferedReader
					.lines( )
					.map( playerLineParser::apply )
					.collect( Collectors.toList( ) );
		} catch ( Exception e ) {
			throw new IllegalArgumentException( "could not parse players", e );
		}
	}

}
