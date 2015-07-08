package io.mstream.roulette.domain.roulette.bet;

import org.junit.Assert;
import org.junit.Test;


public class EvenBetTypeTest {

    private EvenBetType instance = new EvenBetType();

    @Test
    public void shouldMatchWhenPocketNumberIsEven() {
        Assert.assertTrue(instance.test(2));
    }

    @Test
    public void shouldNotMatchWhenPocketNumberIsOdd() {
        Assert.assertFalse(instance.test(3));
    }


}