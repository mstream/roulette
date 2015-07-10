package io.mstream.roulette.output.format.summary;

import io.mstream.roulette.domain.Player;
import io.mstream.roulette.output.format.ObjectToStringFormatter;
import io.mstream.roulette.output.format.TableTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PlayerSummaryFormatter
		implements ObjectToStringFormatter<Player> {

	private final String playerSummaryTemplate;

	@Autowired
	public PlayerSummaryFormatter(
			ObjectToStringFormatter<Integer> tableTemplateBuilder ) {
		this.playerSummaryTemplate = tableTemplateBuilder.apply( 3 );
	}

	@Override public String apply( Player player ) {
		if (player == null) {
			throw new IllegalArgumentException( "player cannot be null" );
		}
		return String.format(
				playerSummaryTemplate,
				player.getName( ),
				player.getTotalWin( ),
				player.getTotalBet( ) );
	}
}
