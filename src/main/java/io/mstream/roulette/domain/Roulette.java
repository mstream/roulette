package io.mstream.roulette.domain;

import io.mstream.roulette.domain.bet.Bet;
import io.mstream.roulette.domain.result.Outcome;
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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
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

	private final Lock resultGeneratorLock = new ReentrantLock( );

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
		if ( bet == null ) {
			throw new IllegalArgumentException( "bet cannot be null" );
		}
		if ( bet.getAmount( ).compareTo( BigDecimal.ZERO ) != 1 ) {
			throw new IllegalArgumentException(
					"bet amount should be a positive number" );
		}
		String playerName = bet.getPlayerName( );
		Player player = players.get( playerName );
		if ( player == null ) {
			throw new IllegalArgumentException(
					"no such player: " + playerName );
		}
		resultGeneratorLock.lock( );
		bets.put( player, bet );
		resultGeneratorLock.unlock( );
	}

	@Scheduled(
			initialDelayString = "${roulette.rollRateInMilliseconds}",
			fixedRateString = "${roulette.rollRateInMilliseconds}" )
	public void generateResult( ) {
		int winningNumber = numbersGenerator.get( );
		resultGeneratorLock.lock( );
		List<PlayerResult> playersResults = calculateResults( winningNumber );
		updatePlayersStatus( playersResults );
		clearBets( );
		resultGeneratorLock.unlock( );
		Result result = new Result( winningNumber, playersResults );
		writeResultsToOutput( result );
	}

	private List<PlayerResult> calculateResults( int winningNumber ) {
		return bets.values( )
				.stream( )
				.map( bet -> playerResultFactory.createResult(
								bet,
								winningNumber )
				)
				.collect( Collectors.toList( ) );
	}

	private void updatePlayersStatus( List<PlayerResult> playerResults ) {
		playerResults
				.stream( )
				.forEach( result -> {
							String playerName = result.getPlayerName( );
							Player player = players.get( playerName );
							BigDecimal currentTotalBet = player.getTotalBet( );
							BigDecimal currentTotalWin = player.getTotalWin( );
							Bet bet = bets.get( player );
							player = new Player.Builder( playerName )
									.withTotalBet( currentTotalBet
											.add( bet.getAmount( ) ) )
									.withTotalWin( result.getOutcome( )
											== Outcome.WIN ?
											currentTotalWin.add( result
													.getWinning( ) ) :
											currentTotalWin )
									.build( );
						}
				);
	}

	private void writeResultsToOutput( Result result ) {
		outputWriter.write( resultFormatter.apply( result ) );
		outputWriter.write( summaryFormatter.apply( players.values( ) ) );
	}

	private void clearBets( ) {
		bets.clear( );
	}

}
