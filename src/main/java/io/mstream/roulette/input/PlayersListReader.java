package io.mstream.roulette.input;

import io.mstream.roulette.domain.Player;
import io.mstream.roulette.input.parsing.player.PlayersLinesParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;


@Component
public class PlayersListReader {

	private static final Logger LOGGER =
			LoggerFactory.getLogger( PlayersListReader.class );

	private final PlayersLinesParser playersLinesParser;

	@Autowired
	public PlayersListReader( PlayersLinesParser playersLinesParser ) {
		this.playersLinesParser = playersLinesParser;
	}

	public List<Player> getPlayers( Path path ) {
		try (InputStream is = Files.newInputStream( path )) {
			return playersLinesParser.apply( is );
		} catch ( Exception e ) {
			LOGGER.error( "error while reading players list file: ", e );
		}
		return Collections.emptyList( );
	}
}
