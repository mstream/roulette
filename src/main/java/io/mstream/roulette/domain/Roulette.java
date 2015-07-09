package io.mstream.roulette.domain;

import io.mstream.roulette.domain.bet.Bet;
import io.mstream.roulette.domain.result.PlayerResult;
import io.mstream.roulette.domain.result.PlayerResultFactory;
import io.mstream.roulette.domain.result.Result;
import io.mstream.roulette.output.RouletteOutputWriter;
import io.mstream.roulette.output.format.result.ResultFormatter;
import io.mstream.roulette.output.format.summary.SummaryFormatter;
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
	private final SummaryFormatter summaryFormatter;
	private final RouletteOutputWriter outputWriter;

	private final Map<String, Player> players;
	private final Map<Player, Bet> bets = new HashMap<>( );

	@Autowired
	public Roulette(
			NumbersGenerator numbersGenerator,
			PlayerResultFactory playerResultFactory,
			ResultFormatter resultFormatter,
			@Value( "#{players}" )
			List<Player> players, SummaryFormatter summaryFormatter,
			RouletteOutputWriter outputWriter ) {
		this.numbersGenerator = numbersGenerator;
		this.playerResultFactory = playerResultFactory;
		this.resultFormatter = resultFormatter;
		this.summaryFormatter = summaryFormatter;
		this.outputWriter = outputWriter;
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
		clearBets( );
		Result result = new Result( winningNumber, playersResults );

		outputWriter.write( resultFormatter.apply( result ) );
		outputWriter.write( summaryFormatter.apply( players.values( ) ) );
	}

	private void clearBets( ) {
		bets.clear( );
	}

}
