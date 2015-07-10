package io.mstream.roulette.output;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class RouletteOutputWriterTest {

	private RouletteOutputWriter instance;

	@Test
	public void shouldForwardOutputToOutputStream( ) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream( );
		instance = new RouletteOutputWriter( new PrintStream( baos ) );
		//
		instance.write( "test" );
		//
		assertEquals( "test", baos.toString( ).trim() );
	}

}