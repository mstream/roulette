package io.mstream.roulette.view.format.summary;

import io.mstream.roulette.domain.Player;
import io.mstream.roulette.view.format.ObjectToStringFormatter;
import io.mstream.roulette.view.format.TableTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PlayerSummaryFormatter
		implements ObjectToStringFormatter<Player> {

	private final String playerSummaryTemplate;

	@Autowired
	public PlayerSummaryFormatter( TableTemplateBuilder tableTemplateBuilder ) {
		this.playerSummaryTemplate = tableTemplateBuilder.build( 3 );
	}

	@Override public String apply( Player player ) {
		return String.format(
				playerSummaryTemplate,
				player.getName( ),
				player.getTotalWin( ),
				player.getTotalBet( ) );
	}
}
