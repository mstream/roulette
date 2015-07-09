package io.mstream.roulette.domain;

import io.mstream.roulette.domain.bet.Bet;
import io.mstream.roulette.domain.result.PlayerResult;
import io.mstream.roulette.domain.result.PlayerResultFactory;
import io.mstream.roulette.domain.result.Result;
import io.mstream.roulette.view.format.ResultFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class Roulette {

	private final NumbersGenerator numbersGenerator;
	private final PlayerResultFactory playerResultFactory;
	private final ResultFormatter resultFormatter;

	private final Map<String, Player> players;
	private final Map<Player, Bet> bets = new HashMap<>( );

	@Autowired
	public Roulette(
			NumbersGenerator numbersGenerator,
			PlayerResultFactory playerResultFactory,
			ResultFormatter resultFormatter,
			@Value( "#{players}" )
			List<Player> players ) {
		this.numbersGenerator = numbersGenerator;
		this.playerResultFactory = playerResultFactory;
		this.resultFormatter = resultFormatter;
		this.players = players
				.stream( )
				.collect( Collectors.toMap(
								Player::getName,
								Function.<Player>identity( ) )
				);
	}

	public void placeBet( Bet bet ) {
		String playerName = bet.getPlayerName( );
		Player player = players.get( playerName );
		if ( player == null ) {
			throw new IllegalArgumentException(
					"no such player: " + playerName );
		}
		bets.put( player, bet );
	}

	@Scheduled(
			initialDelayString = "${roulette.rollRateInMilliseconds}",
			fixedRateString = "${roulette.rollRateInMilliseconds}" )
	public void roll( ) {
		int winningNumber = numbersGenerator.get( );
		List<PlayerResult> playersResults = bets.values( )
				.stream( )
				.map( bet -> playerResultFactory
						.createResult( bet, winningNumber ) )
				.collect( Collectors.toList( ) );
		Result result = new Result( winningNumber, playersResults );
		System.out.println( resultFormatter.apply( result ) );
	}

}
