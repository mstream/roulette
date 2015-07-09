package io.mstream.roulette;

import io.mstream.roulette.input.PlayerInputReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

	public static void main( String[] args ) {
		ApplicationContext appCtx =
				new AnnotationConfigApplicationContext( "io.mstream.roulette" );
		PlayerInputReader inputReader =
				appCtx.getBean( PlayerInputReader.class );
		inputReader.interceptInput( );
	}

}
