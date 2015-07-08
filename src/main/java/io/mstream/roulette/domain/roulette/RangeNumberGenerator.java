package io.mstream.roulette.domain.roulette;

import java.security.SecureRandom;

class RangeNumberGenerator implements NumbersGenerator {

    private final int upperBound;
    private final int lowerBound;
    private final SecureRandom random = new SecureRandom();

    RangeNumberGenerator(int minNumber, int maxNumber) {
        assert minNumber <= maxNumber;
        this.upperBound = maxNumber - minNumber + 1;
        this.lowerBound = minNumber;
    }

    @Override
    public int getNext() {
        return random.nextInt(upperBound) + lowerBound;
    }

}
