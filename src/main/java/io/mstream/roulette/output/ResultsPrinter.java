package io.mstream.roulette.output;

import io.mstream.roulette.domain.Roulette;
import io.mstream.roulette.domain.event.ResultsGenerated;
import io.mstream.roulette.output.format.result.ResultFormatter;
import io.mstream.roulette.output.format.summary.SummaryFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;


@Component
public class ResultsPrinter implements Observer {

	private final ResultFormatter resultFormatter;
	private final SummaryFormatter summaryFormatter;
	private final RouletteOutputWriter outputWriter;

	@Autowired
	public ResultsPrinter(
			Roulette roulette,
			ResultFormatter resultFormatter,
			SummaryFormatter summaryFormatter,
			RouletteOutputWriter outputWriter ) {
		this.resultFormatter = resultFormatter;
		this.summaryFormatter = summaryFormatter;
		this.outputWriter = outputWriter;
		roulette.addObserver( this );
	}

	@Override public void update( Observable o, Object arg ) {
		if ( arg instanceof ResultsGenerated ) {
			printGeneratedResults( ( ResultsGenerated ) arg );
		} else {
			throw new IllegalArgumentException( "unknown event type" );
		}
	}

	private void printGeneratedResults( ResultsGenerated resultsGenerated ) {
		outputWriter.write(
				resultFormatter.apply( resultsGenerated.getResult( ) ) );
		outputWriter.write(
				summaryFormatter.apply( resultsGenerated.getPlayers( ) ) );
	}
}
