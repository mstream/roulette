package io.mstream.roulette;

import io.mstream.roulette.domain.roulette.Roulette;
import io.mstream.roulette.domain.roulette.bet.Bet;
import io.mstream.roulette.domain.roulette.bet.BetTypeFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;


public class Main {

	public static void main( String[] args ) {
		ApplicationContext appCtx =
				new AnnotationConfigApplicationContext( "io.mstream.roulette" );
		Roulette roulette = appCtx.getBean( Roulette.class );
		roulette.placeBet( new Bet( "Foo", new BetTypeFactory( )
				.fromString( "ODD" ), BigDecimal.ONE ) );
	}

}
