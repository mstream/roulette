package io.mstream.roulette.parsing;


import java.util.function.Function;

public abstract class StringParser<T> implements Function<String, T> {

    @Override
    public <V> Function<V, T> compose(Function<? super V, ? extends String> before) {
        return null;
    }

    @Override
    public <V> Function<String, V> andThen(Function<? super T, ? extends V> after) {
        return null;
    }
}
