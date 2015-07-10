package io.mstream.roulette.domain.bet;

import org.junit.Assert;
import org.junit.Test;


public class OddBetTypeTest {

    private OddBetType instance = new OddBetType();

    @Test
    public void shouldMatchWhenPocketNumberIsOdd() {
        Assert.assertTrue(instance.test(1));
        Assert.assertTrue(instance.test(3));
        Assert.assertTrue(instance.test(5));
        Assert.assertTrue(instance.test(7));    }

    @Test
    public void shouldNotMatchWhenPocketNumberIsEven() {
        Assert.assertFalse(instance.test(2));
        Assert.assertFalse(instance.test(4));
        Assert.assertFalse(instance.test(6));
        Assert.assertFalse(instance.test(8));
    }


}