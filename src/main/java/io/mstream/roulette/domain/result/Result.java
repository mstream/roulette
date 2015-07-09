package io.mstream.roulette.domain.result;

import java.util.Collections;
import java.util.List;


public class Result {

	private final int winningNumber;
	private final List<PlayerResult> playerResults;

	public Result( int winningNumber, List<PlayerResult> playerResults ) {
		this.winningNumber = winningNumber;
		this.playerResults = Collections.unmodifiableList( playerResults );
	}

	public int getWinningNumber( ) {
		return winningNumber;
	}

	public List<PlayerResult> getPlayerResults( ) {
		return playerResults;
	}
}
