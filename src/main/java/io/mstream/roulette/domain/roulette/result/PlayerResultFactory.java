package io.mstream.roulette.domain.roulette.result;

import io.mstream.roulette.domain.roulette.Bet;
import io.mstream.roulette.domain.roulette.prize.PrizeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static io.mstream.roulette.domain.roulette.result.Outcome.LOSE;
import static io.mstream.roulette.domain.roulette.result.Outcome.WIN;

@Component
public class PlayerResultFactory {

	private final PrizeCalculator prizeCalculator;

	@Autowired
	public PlayerResultFactory( PrizeCalculator prizeCalculator ) {
		this.prizeCalculator = prizeCalculator;
	}

	public PlayerResult createResult( Bet bet, int winningNumber ) {
		boolean isWinning = bet.getType( ).test( winningNumber );
		return new PlayerResult(
				bet.getPlayerName( ),
				bet.getType( ),
				isWinning ? WIN : LOSE,
				isWinning ? prizeCalculator.apply( bet ) : BigDecimal.ZERO );
	}
}
