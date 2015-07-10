package io.mstream.roulette.domain;

import java.security.SecureRandom;
import java.util.Random;


public class RangeNumberGenerator implements NumbersGenerator {

    private final int upperBound;
    private final int lowerBound;
    private final Random random = new SecureRandom();

    public RangeNumberGenerator(int minNumber, int maxNumber) {
        assert minNumber <= maxNumber;
        this.upperBound = maxNumber - minNumber + 1;
        this.lowerBound = minNumber;
    }

    @Override public Integer get( ) {
        return random.nextInt(upperBound) + lowerBound;
    }
}
