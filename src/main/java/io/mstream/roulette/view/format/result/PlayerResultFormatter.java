package io.mstream.roulette.view.format.result;

import io.mstream.roulette.domain.result.PlayerResult;
import io.mstream.roulette.view.format.ObjectToStringFormatter;
import io.mstream.roulette.view.format.TableTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PlayerResultFormatter
		implements ObjectToStringFormatter<PlayerResult> {

	private final String playerResultTemplate;

	@Autowired
	public PlayerResultFormatter( TableTemplateBuilder tableTemplateBuilder ) {
		this.playerResultTemplate = tableTemplateBuilder.build( 4 );
	}

	@Override public String apply( PlayerResult result ) {
		return String.format(
				playerResultTemplate,
				result.getPlayerName( ),
				result.getBetType( ),
				result.getOutcome( ),
				result.getWinning( ) );
	}
}
