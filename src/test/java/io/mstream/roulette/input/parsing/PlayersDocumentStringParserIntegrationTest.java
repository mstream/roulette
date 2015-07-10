package io.mstream.roulette.input.parsing;

import io.mstream.roulette.domain.Player;
import io.mstream.roulette.input.parsing.player.PlayerLineStringParser;
import io.mstream.roulette.input.parsing.player.PlayersLinesParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class PlayersDocumentStringParserIntegrationTest {

	private PlayersLinesParser instance =
			new PlayersLinesParser( new PlayerLineStringParser( ) );

	@Test
	public void test( ) {
		Player john = new Player.Builder( "John" )
				.build( );
		Player mike = new Player.Builder( "Mike" )
				.build( );
		Player doug = new Player.Builder( "Doug" )
				.withTotalWin( BigDecimal.ONE )
				.withTotalBet( BigDecimal.TEN )
				.build( );
		String playersStr = "John\nMike\nDoug,1,10";
		//
		List<Player> players =
				instance.apply(
						new ByteArrayInputStream(
								playersStr
										.getBytes( StandardCharsets.UTF_8 ) ) );
		//
		Assert.assertNotNull( players );
		Assert.assertEquals( 3, players.size( ) );
		Assert.assertEquals( john, players.get( 0 ) );
		Assert.assertEquals( mike, players.get( 1 ) );
		Assert.assertEquals( doug, players.get( 2 ) );

	}
}