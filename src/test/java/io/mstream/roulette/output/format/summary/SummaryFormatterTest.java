package io.mstream.roulette.output.format.summary;

import io.mstream.roulette.domain.Player;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;


public class SummaryFormatterTest {

	private SummaryFormatter instance =
			new SummaryFormatter(
					player -> String.format(
							"%s|%.1f|%.1f",
							player.getName( ),
							player.getTotalWin( ),
							player.getTotalBet( )
					),
					columnNumber -> "%s, %s, %s"
			);

	@Test
	public void shouldFormatResultProperly( ) {
		//
		String summaryStr = instance.apply( Arrays.asList(
				new Player.Builder( "Foo" )
						.withTotalWin( new BigDecimal( 2 ) )
						.withTotalBet( new BigDecimal( 4 ) )
						.build( ),
				new Player.Builder( "Bar" )
						.withTotalWin( new BigDecimal( 8 ) )
						.withTotalBet( new BigDecimal( 16 ) )
						.build( )
		) );
		//
		Assert.assertEquals(
						"Player, Total Win, Total Bet\n" +
						"---\n" +
						"Foo|2.0|4.0\n" +
						"Bar|8.0|16.0\n",
				summaryStr );
	}

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailOnNullValue( ) {
		//
		instance.apply( null );
		//
	}

}