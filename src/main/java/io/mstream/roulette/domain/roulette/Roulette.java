package io.mstream.roulette.domain.roulette;

import io.mstream.roulette.domain.roulette.bet.Bet;
import io.mstream.roulette.domain.roulette.result.PlayerResult;
import io.mstream.roulette.domain.roulette.result.PlayerResultFactory;
import io.mstream.roulette.domain.roulette.result.Result;
import io.mstream.roulette.view.format.ResultFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Roulette {

	private final NumbersGenerator numbersGenerator;
	private final PlayerResultFactory playerResultFactory;
	private final ResultFormatter resultFormatter;

	private final Map<String, Player> players = new HashMap<>( );
	private final Map<Player, Bet> bets = new HashMap<>( );

	@Autowired
	public Roulette( NumbersGenerator numbersGenerator,
			PlayerResultFactory playerResultFactory,
			ResultFormatter resultFormatter ) {
		this.numbersGenerator = numbersGenerator;
		this.playerResultFactory = playerResultFactory;
		this.resultFormatter = resultFormatter;
	}

	public void addPlayer( Player player ) {
		if ( players.containsKey( player.getName( ) ) ) {
			throw new IllegalArgumentException( );
		}
		players.put( player.getName( ), player );
	}

	public void placeBet( Bet bet ) {
		String playerName = bet.getPlayerName( );
		Player player = players.get( playerName );
		if ( player == null ) {
			throw new IllegalArgumentException( "no such player: " + playerName );
		}
		bets.put( player, bet );
	}

	public void roll( ) {
		int winningNumber = numbersGenerator.get( );
		List<PlayerResult> playersResults = bets.values( )
				.stream( )
				.map( bet -> playerResultFactory.createResult( bet, winningNumber ) )
				.collect( Collectors.toList( ) );
		Result result = new Result( winningNumber, playersResults );
		System.out.println(resultFormatter.apply( result ) );
	}

}
