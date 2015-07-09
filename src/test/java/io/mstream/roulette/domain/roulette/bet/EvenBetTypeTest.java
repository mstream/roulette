package io.mstream.roulette.domain.roulette.bet;

import org.junit.Assert;
import org.junit.Test;


public class EvenBetTypeTest {

    private EvenBetType instance = new EvenBetType();

    @Test
    public void shouldMatchWhenPocketNumberIsEven() {
        Assert.assertTrue(instance.test(2));
        Assert.assertTrue(instance.test(4));
        Assert.assertTrue(instance.test(6));
        Assert.assertTrue(instance.test(8));
    }

    @Test
    public void shouldNotMatchWhenPocketNumberIsOdd() {
        Assert.assertFalse(instance.test(1));
        Assert.assertFalse(instance.test(3));
        Assert.assertFalse(instance.test(5));
        Assert.assertFalse(instance.test(7));
    }


}