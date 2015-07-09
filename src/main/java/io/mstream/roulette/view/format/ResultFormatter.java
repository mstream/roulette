package io.mstream.roulette.view.format;

import io.mstream.roulette.domain.roulette.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class ResultFormatter implements ObjectToStringFormatter<Result> {

	private final PlayerResultFormatter playerResultFormatter;

	private final String headerTemplate =
			"Number: %d\nPlayer Bet Outcome Winnings\n---\n";

	@Autowired
	public ResultFormatter( PlayerResultFormatter playerResultFormatter ) {
		this.playerResultFormatter = playerResultFormatter;
	}

	@Override public String apply( Result result ) {

		String playersResultsStr = result.getPlayerResults( )
				.stream( )
				.map( playerResultFormatter::apply )
				.collect( Collectors.joining("\n") );

		return String.format( headerTemplate, result.getWinningNumber( ) ) +
				playersResultsStr + "\n";
	}
}
