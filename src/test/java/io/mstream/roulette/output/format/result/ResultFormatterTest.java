package io.mstream.roulette.output.format.result;

import io.mstream.roulette.domain.bet.BetTypeFactory;
import io.mstream.roulette.domain.result.Outcome;
import io.mstream.roulette.domain.result.PlayerResult;
import io.mstream.roulette.domain.result.Result;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;


public class ResultFormatterTest {

	private ResultFormatter instance =
			new ResultFormatter(
					playerResult -> String.format(
							"%s|%s|%s|%.1f",
							playerResult.getPlayerName( ),
							playerResult.getBetType( ),
							playerResult.getOutcome( ),
							playerResult.getWinning( )
					),
					columnNumber -> "%s, %s, %s, %s"
			);

	@Test
	public void shouldFormatResultProperly( ) {
		BetTypeFactory betTypeFactory = new BetTypeFactory( );
		Result result =
				new Result(
						10,
						Arrays.asList(
								new PlayerResult(
										"Foo",
										betTypeFactory.apply( "EVEN" ),
										Outcome.WIN,
										new BigDecimal( 4.0 )
								),
								new PlayerResult(
										"Bar",
										betTypeFactory.apply( "ODD" ),
										Outcome.LOSE,
										new BigDecimal( 0.0 )
								),
								new PlayerResult(
										"Biz",
										betTypeFactory.apply( "10" ),
										Outcome.WIN,
										new BigDecimal( 72 )
								)
						)
				);
		//
		String resultStr = instance.apply( result );
		//
		Assert.assertEquals(
				"Number: 10\n" +
						"Player, Bet, Outcome, Winnings\n" +
						"---\n" +
						"Foo|EVEN|WIN|4.0\n" +
						"Bar|ODD|LOSE|0.0\n" +
						"Biz|10|WIN|72.0\n",
				resultStr );
	}

	@Test( expected = IllegalArgumentException.class )
	public void shouldFailOnNullValue( ) {
		//
		instance.apply( null );
		//
	}

}