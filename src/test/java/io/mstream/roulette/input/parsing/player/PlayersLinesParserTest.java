package io.mstream.roulette.input.parsing.player;

import io.mstream.roulette.domain.Player;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class PlayersLinesParserTest {

	private PlayersLinesParser instance =
			new PlayersLinesParser( s -> new Player.Builder( s ).build( ) );

	@Test
	public void shouldParseMultipleLines( ) {
		String lines = "Foo\nBar\nBiz";
		//
		List<Player> parsedPlayers = instance.apply( lines );
		//
		assertNotNull( parsedPlayers );
		assertEquals( 3, parsedPlayers.size( ) );
		assertEquals( "Foo", parsedPlayers.get( 0 ).getName() );
		assertEquals( "Bar", parsedPlayers.get( 1 ).getName() );
		assertEquals( "Biz", parsedPlayers.get( 2 ).getName() );
	}

	@Test( expected = Exception.class )
	public void shouldFailOnNullValue( ) {
		//
		instance.apply( null );
		//
	}

}