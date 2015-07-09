package io.mstream.roulette;

import io.mstream.roulette.domain.roulette.Roulette;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {

	public static void main( String[] args ) {
		ApplicationContext appCtx =
				new AnnotationConfigApplicationContext( "io.mstream.roulette" );
		Roulette roulette = appCtx.getBean( Roulette.class );
		ScheduledExecutorService executor = Executors
				.newSingleThreadScheduledExecutor( );
		executor.scheduleAtFixedRate(
				( ) -> roulette.roll( ),
				3, 3,
				TimeUnit.SECONDS );
	}

}
