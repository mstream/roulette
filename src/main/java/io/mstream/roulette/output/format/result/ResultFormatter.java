package io.mstream.roulette.output.format.result;

import io.mstream.roulette.domain.result.PlayerResult;
import io.mstream.roulette.domain.result.Result;
import io.mstream.roulette.output.format.ObjectToStringFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class ResultFormatter implements ObjectToStringFormatter<Result> {

	private final ObjectToStringFormatter<PlayerResult> playerResultFormatter;
	private final String headerTemplate;

	@Autowired
	public ResultFormatter(
			ObjectToStringFormatter<PlayerResult> playerResultFormatter,
			ObjectToStringFormatter<Integer> tableTemplateBuilder ) {
		this.playerResultFormatter = playerResultFormatter;
		this.headerTemplate =
				"Number: %d\n" + tableTemplateBuilder.apply( 4 ) + "\n---\n";
	}

	@Override public String apply( Result result ) {
		if ( result == null ) {
			throw new IllegalArgumentException( "result cannot be null" );
		}
		String playersResultsStr = result.getPlayerResults( )
				.stream( )
				.map( playerResultFormatter::apply )
				.collect( Collectors.joining( "\n" ) );

		return String.format(
				headerTemplate,
				result.getWinningNumber( ),
				"Player",
				"Bet",
				"Outcome",
				"Winnings" ) +
				playersResultsStr + "\n";
	}
}
