package io.mstream.roulette.domain.random;

import java.security.SecureRandom;
import java.util.Random;


public class SecureNumbersGenerator implements RandomNumbersGenerator {

	private final Random random = new SecureRandom( );

	@Override public int nextInt( int bound ) {
		return random.nextInt( bound );
	}
}
