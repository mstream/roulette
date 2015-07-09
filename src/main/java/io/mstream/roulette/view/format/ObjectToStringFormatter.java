package io.mstream.roulette.view.format;

import java.util.function.Function;


public interface ObjectToStringFormatter<T> extends Function<T, String> {}
