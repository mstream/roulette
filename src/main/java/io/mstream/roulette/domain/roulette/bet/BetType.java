package io.mstream.roulette.domain.roulette.bet;


import java.util.function.Predicate;

public abstract class BetType implements Predicate<Integer> {

    @Override
    public Predicate<Integer> and(Predicate<? super Integer> other) {
        return null;
    }

    @Override
    public Predicate<Integer> negate() {
        return null;
    }

    @Override
    public Predicate<Integer> or(Predicate<? super Integer> other) {
        return null;
    }
}
