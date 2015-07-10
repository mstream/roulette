package io.mstream.roulette.input.parsing.player;


import java.io.InputStream;
import java.util.function.Function;


public interface InputStreamParser<T> extends Function<InputStream, T> {}
