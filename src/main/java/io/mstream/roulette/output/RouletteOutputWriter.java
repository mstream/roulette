package io.mstream.roulette.output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintStream;


@Component
public class RouletteOutputWriter {

	private final PrintStream printStream;

	@Autowired
	public RouletteOutputWriter( PrintStream printStream ) {
		this.printStream = printStream;
	}

	public void write( String output ) {
		printStream.println( output );
	}
}
