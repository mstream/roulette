package io.mstream.roulette.view.format;

import io.mstream.roulette.domain.roulette.result.PlayerResult;
import org.springframework.stereotype.Component;


@Component
public class PlayerResultFormatter
		implements ObjectToStringFormatter<PlayerResult> {

	private final String resultTemplate =
			"%s %s %s %s";

	@Override public String apply( PlayerResult result ) {
		return String.format(
				resultTemplate,
				result.getPlayerName( ),
				result.getBetType( ),
				result.getOutcome( ),
				result.getWinning( ) );
	}
}
