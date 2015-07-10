package io.mstream.roulette.output.format.summary;

import io.mstream.roulette.domain.Player;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;


public class PlayerSummaryFormatterTest {
	private PlayerSummaryFormatter instance =
			new PlayerSummaryFormatter( columnNumber -> "%s, %.1f, %.1f" );

	@Test
	public void shouldFormatResultProperly( ) {
		Player player = new Player.Builder( "Foo" )
				.withTotalWin( new BigDecimal( 2 ) )
				.withTotalBet( new BigDecimal( 4 ) )
				.build( );
		//
		String summaryStr = instance.apply( player );
		//
		Assert.assertEquals( "Foo, 2.0, 4.0", summaryStr );
	}

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailOnNullValue( ) {
		//
		instance.apply( null );
		//
	}
}