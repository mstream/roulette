package io.mstream.roulette.output.format.result;

import io.mstream.roulette.domain.bet.BetTypeFactory;
import io.mstream.roulette.domain.result.Outcome;
import io.mstream.roulette.domain.result.PlayerResult;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;


public class PlayerResultFormatterTest {

	private PlayerResultFormatter instance =
			new PlayerResultFormatter( columnNumber -> "%s, %s, %s, %.1f" );

	@Test
	public void shouldFormatResultProperly( ) {
		PlayerResult result =
				new PlayerResult(
						"Foo",
						new BetTypeFactory( ).apply( "ODD" ),
						Outcome.WIN,
						new BigDecimal( 4 )
				);
		//
		String resultStr = instance.apply( result );
		//
		Assert.assertEquals( "Foo, ODD, WIN, 4.0", resultStr );
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailOnNullValue( ) {
		//
		instance.apply( null );
		//
	}

}