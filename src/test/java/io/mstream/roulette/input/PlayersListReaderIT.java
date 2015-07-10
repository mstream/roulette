package io.mstream.roulette.input;

import io.mstream.roulette.config.RouletteConfiguration;
import io.mstream.roulette.domain.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = RouletteConfiguration.class )
public class PlayersListReaderIT {

	@Autowired
	private PlayersListReader instance;

	@Test
	public void shouldReadListWithNamesOnly( ) throws URISyntaxException {
		String fileName = "playersList_namesOnly.csv";
		//
		List<Player> players = instance.getPlayers( Paths.get(
				ClassLoader.getSystemResource( fileName ).toURI( ) ) );
		//
		assertNotNull( players );
		assertEquals( 3, players.size( ) );
		assertEquals( "Foo", players.get( 0 ).getName( ) );
		assertEquals( "Bar", players.get( 1 ).getName( ) );
		assertEquals( "Biz", players.get( 2 ).getName( ) );
		assertEquals(
				0,
				players.get( 0 ).getTotalWin( ).doubleValue( ),
				0 );
		assertEquals(
				0,
				players.get( 1 ).getTotalWin( ).doubleValue( ),
				0 );
		assertEquals(
				0,
				players.get( 2 ).getTotalWin( ).doubleValue( ),
				0 );
		assertEquals(
				0,
				players.get( 0 ).getTotalBet( ).doubleValue( ),
				0 );
		assertEquals(
				0,
				players.get( 1 ).getTotalBet( ).doubleValue( ),
				0 );
		assertEquals(
				0,
				players.get( 2 ).getTotalBet( ).doubleValue( ),
				0 );
	}

	@Test
	public void shouldReadListWithHistory( ) throws URISyntaxException {
		String fileName = "playersList_withHistory.csv";
		//
		List<Player> players = instance.getPlayers( Paths.get(
				ClassLoader.getSystemResource( fileName ).toURI( ) ) );
		//
		assertNotNull( players );
		assertEquals( 3, players.size( ) );
		assertEquals( "Foo", players.get( 0 ).getName( ) );
		assertEquals( "Bar", players.get( 1 ).getName( ) );
		assertEquals( "Biz", players.get( 2 ).getName( ) );
		assertEquals(
				1,
				players.get( 0 ).getTotalWin( ).doubleValue( ),
				0 );
		assertEquals(
				2,
				players.get( 1 ).getTotalWin( ).doubleValue( ),
				0 );
		assertEquals(
				0,
				players.get( 2 ).getTotalWin( ).doubleValue( ),
				0 );
		assertEquals(
				2,
				players.get( 0 ).getTotalBet( ).doubleValue( ),
				0 );
		assertEquals(
				1,
				players.get( 1 ).getTotalBet( ).doubleValue( ),
				0 );
		assertEquals(
				0,
				players.get( 2 ).getTotalBet( ).doubleValue( ),
				0 );
	}

}