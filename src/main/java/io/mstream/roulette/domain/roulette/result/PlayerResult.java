package io.mstream.roulette.domain.roulette.result;

import io.mstream.roulette.domain.roulette.bet.BetType;

import java.math.BigDecimal;


public class PlayerResult {
	private final String playerName;
	private final BetType betType;
	private final Outcome outcome;
	private final BigDecimal winning;

	public PlayerResult(
			String playerName,
			BetType betType,
			Outcome outcome,
			BigDecimal winning ) {
		this.playerName = playerName;
		this.betType = betType;
		this.outcome = outcome;
		this.winning = winning;
	}
}
