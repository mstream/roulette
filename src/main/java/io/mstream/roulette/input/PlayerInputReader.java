package io.mstream.roulette.input;

import io.mstream.roulette.domain.Roulette;
import io.mstream.roulette.domain.bet.Bet;
import io.mstream.roulette.input.parsing.bet.BetLineStringParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;


@Component
public class PlayerInputReader {

	private final static Logger LOGGER =
			LoggerFactory.getLogger( PlayerInputReader.class );
	private final Roulette roulette;
	private final InputStream inputStream;
	private final BetLineStringParser betParser;

	@Autowired
	public PlayerInputReader(
			Roulette roulette,
			InputStream inputStream,
			BetLineStringParser betParser ) {
		this.roulette = roulette;
		this.inputStream = inputStream;
		this.betParser = betParser;
	}

	public void interceptInput( ) {
		try (BufferedReader reader =
				new BufferedReader( new InputStreamReader( inputStream ) )) {
			String line;
			while ( (line = reader.readLine( )) != null ) {
				Optional<Bet> parsedBetOpt = betParser.apply( line );
				if ( !parsedBetOpt.isPresent( ) ) {
					System.err.println( "could not parse the bet" );
					continue;
				}
				Bet parsedBet = parsedBetOpt.get( );
				roulette.placeBet( parsedBet );
			}
		} catch ( IOException e ) {
			LOGGER.error( "error while reading user input", e );
		}
	}

}
