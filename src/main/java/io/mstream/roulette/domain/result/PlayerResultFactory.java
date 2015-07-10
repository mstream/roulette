package io.mstream.roulette.domain.result;

import io.mstream.roulette.domain.bet.Bet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Function;


@Component
public class PlayerResultFactory {

	private final Function<Bet, BigDecimal> prizeCalculator;

	@Autowired
	public PlayerResultFactory( Function<Bet, BigDecimal> prizeCalculator ) {
		this.prizeCalculator = prizeCalculator;
	}

	public PlayerResult createResult( Bet bet, int winningNumber ) {
		if ( bet == null ) {
			throw new IllegalArgumentException( "bet can't be null" );
		}
		boolean isWinning = bet.getType( ).test( winningNumber );
		return new PlayerResult(
				bet.getPlayerName( ),
				bet.getType( ),
				isWinning ? Outcome.WIN : Outcome.LOSE,
				isWinning ? prizeCalculator.apply( bet ) : BigDecimal.ZERO );
	}
}
