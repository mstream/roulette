package io.mstream.roulette.domain.event;

import io.mstream.roulette.domain.Player;
import io.mstream.roulette.domain.result.Result;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class ResultsGenerated {

	private final Result result;
	private final List<Player> players;

	public ResultsGenerated( Result result, Collection<Player> players ) {
		this.result = result;
		this.players = players
				.stream( )
				.map( player -> new Player.Builder( player.getName( ) )
						.withTotalWin( player.getTotalWin( ) )
						.withTotalBet( player.getTotalBet( ) )
						.build( ) )
				.collect( Collectors.toList( ) );
	}

	public Result getResult( ) {
		return result;
	}

	public List<Player> getPlayers( ) {
		return players;
	}
}
