package io.mstream.roulette.view.formatter;

import io.mstream.roulette.domain.roulette.result.Result;
import org.springframework.stereotype.Component;


@Component
public class ResultFormatter implements ObjectToStringFormatter<Result> {

	@Override public String apply( Result result ) {
		StringBuilder sb = new StringBuilder( );
		sb.append( "Number: " + result.getWinningNumber() );
		return sb.toString();
	}
}
