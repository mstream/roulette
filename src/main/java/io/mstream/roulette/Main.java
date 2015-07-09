package io.mstream.roulette;

import io.mstream.roulette.domain.roulette.bet.Bet;
import io.mstream.roulette.domain.roulette.Player;
import io.mstream.roulette.domain.roulette.Roulette;
import io.mstream.roulette.domain.roulette.bet.BetTypeFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {

	public static void main( String[] args ) {
		ApplicationContext appCtx =
				new AnnotationConfigApplicationContext( "io.mstream.roulette" );
		Roulette roulette = appCtx.getBean( Roulette.class );
		Player player = new Player.Builder( "Dude" ).build( );
		roulette.addPlayer( player );
		roulette.placeBet( new Bet( player.getName(), new BetTypeFactory()
				.fromString( "ODD" ), BigDecimal.ONE ) );
		ScheduledExecutorService executor = Executors
				.newSingleThreadScheduledExecutor( );
		executor.scheduleAtFixedRate(
				( ) -> roulette.roll( ),
				3, 3,
				TimeUnit.SECONDS );
	}

}
