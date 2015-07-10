package io.mstream.roulette.output;

import io.mstream.roulette.config.RouletteConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = RouletteConfiguration.class )
public class ResultsPrinterIT {

	@Autowired
	private ResultsPrinter instance;

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailOnUnknownEventUpdate( ) {
		//
		instance.update( null, new Object( ) );
		//
	}
}