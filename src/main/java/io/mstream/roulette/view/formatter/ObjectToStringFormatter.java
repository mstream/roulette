package io.mstream.roulette.view.formatter;

import java.util.function.Function;


public interface ObjectToStringFormatter<T> extends Function<T, String> {}
