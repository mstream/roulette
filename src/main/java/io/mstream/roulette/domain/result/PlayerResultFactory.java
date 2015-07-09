package io.mstream.roulette.domain.result;

import io.mstream.roulette.domain.bet.Bet;
import io.mstream.roulette.domain.prize.PrizeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


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
				isWinning ? Outcome.WIN : Outcome.LOSE,
				isWinning ? prizeCalculator.apply( bet ) : BigDecimal.ZERO );
	}
}
