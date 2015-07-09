package io.mstream.roulette.domain.bet;

import org.springframework.stereotype.Component;


@Component
public class BetTypeFactory {

    private final String numberRegex = "\\d+";
    private final BetType evenBetType = new EvenBetType();
    private final BetType oddBetType = new OddBetType();

    public BetType fromString(String betString) {
        if (betString == null) {
            throw new IllegalArgumentException();
        }
        if ("EVEN".equals(betString)) {
            return evenBetType;
        } else if ("ODD".equals(betString)) {
            return oddBetType;
        } else if (betString.matches(numberRegex)) {
            int number = Integer.parseInt(betString);
            if (number < 1 || number > 36) {
                throw new IllegalArgumentException("Pocket number must be between 1 and 36");
            }
            return new NumberBetType(number);
        } else {
            throw new IllegalArgumentException("Unknown bet type: " + betString);
        }
    }
}
