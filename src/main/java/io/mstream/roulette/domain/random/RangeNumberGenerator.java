package io.mstream.roulette.domain.random;

public class RangeNumberGenerator implements NumbersGenerator {

	private final RandomNumbersGenerator random;
	private final int upperBound;
	private final int lowerBound;

	public RangeNumberGenerator(
			RandomNumbersGenerator random,
			int minNumber,
			int maxNumber
	) {
		this.random = random;
		if ( minNumber > maxNumber ) {
			throw new IllegalArgumentException(
					"minNumber must be lesser or equal maxNumber" );
		}
		this.upperBound = maxNumber - minNumber + 1;
		this.lowerBound = minNumber;
	}

	@Override public Integer get( ) {
		return random.nextInt( upperBound ) + lowerBound;
	}
}
