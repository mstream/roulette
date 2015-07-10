package io.mstream.roulette.output.format.result;

import io.mstream.roulette.domain.result.PlayerResult;
import io.mstream.roulette.output.format.ObjectToStringFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
public class PlayerResultFormatter
		implements ObjectToStringFormatter<PlayerResult> {

	private final String playerResultTemplate;

	@Autowired
	public PlayerResultFormatter(
			ObjectToStringFormatter<Integer> tableTemplateBuilder ) {
		this.playerResultTemplate = tableTemplateBuilder.apply( 4 );
	}

	@Override public String apply( PlayerResult result ) {
		if ( result == null ) {
			throw new IllegalArgumentException( "result cannot be null" );
		}
		return String.format(
				playerResultTemplate,
				result.getPlayerName( ),
				result.getBetType( ),
				result.getOutcome( ),
				result.getWinning( ) );
	}
}
