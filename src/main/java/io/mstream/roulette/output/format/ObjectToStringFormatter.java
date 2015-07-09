package io.mstream.roulette.output.format;

import java.util.function.Function;


public interface ObjectToStringFormatter<T> extends Function<T, String> {}
