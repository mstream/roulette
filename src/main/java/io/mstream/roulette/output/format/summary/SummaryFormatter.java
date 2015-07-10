package io.mstream.roulette.output.format.summary;

import io.mstream.roulette.domain.Player;
import io.mstream.roulette.output.format.ObjectToStringFormatter;
import io.mstream.roulette.output.format.TableTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;


@Component
public class SummaryFormatter
		implements ObjectToStringFormatter<Collection<Player>> {

	private final ObjectToStringFormatter<Player> playerSummaryFormatter;

	private final String headerTemplate;

	@Autowired
	public SummaryFormatter(
			ObjectToStringFormatter<Player> playerSummaryFormatter,
			ObjectToStringFormatter<Integer> tableTemplateBuilder ) {
		this.playerSummaryFormatter = playerSummaryFormatter;
		this.headerTemplate = tableTemplateBuilder.apply( 3 ) + "\n";
	}

	@Override public String apply( Collection<Player> players ) {

		if ( players == null ) {
			throw new IllegalArgumentException( "players cannot be null" );
		}

		String playersSummaryStr = players
				.stream( )
				.map( playerSummaryFormatter::apply )
				.collect( Collectors.joining( "\n" ) );

		return String.format(
				headerTemplate,
				"Player",
				"Total Win",
				"Total Bet" )
				+ "---\n"
				+ playersSummaryStr + "\n";
	}
}
