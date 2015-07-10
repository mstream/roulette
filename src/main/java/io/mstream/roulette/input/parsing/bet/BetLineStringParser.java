package io.mstream.roulette.input.parsing.bet;

import io.mstream.roulette.domain.bet.Bet;
import io.mstream.roulette.domain.bet.BetType;
import io.mstream.roulette.input.parsing.StringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class BetLineStringParser implements StringParser<Optional<Bet>> {

	private final Pattern linePattern = Pattern.compile(
			"(?<playerName>\\w+)(\\s+(?<bet>\\w+|d+))(\\s+(?<amount>\\d+(\\.\\d+)?))\\s*" );

	private final Function<String, BetType> betTypeFactory;

	@Autowired
	public BetLineStringParser( Function<String, BetType> betTypeFactory ) {
		this.betTypeFactory = betTypeFactory;
	}

	@Override
	public Optional<Bet> apply( String betLine ) {
		if ( betLine == null ) {
			throw new IllegalArgumentException( );
		}
		Matcher matcher = linePattern.matcher( betLine );
		if ( !matcher.matches( ) ) {
			return Optional.empty( );
		}
		String playerName = matcher.group( "playerName" );
		String betStr = matcher.group( "bet" );
		String amountStr = matcher.group( "amount" );

		return Optional.of(
				new Bet(
						playerName,
						betTypeFactory.apply( betStr ),
						new BigDecimal( amountStr )
				)
		);
	}

}
