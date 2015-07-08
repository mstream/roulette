package io.mstream.roulette.domain.roulette.bet;

import org.junit.Assert;
import org.junit.Test;


public class OddBetTypeTest {

    private OddBetType instance = new OddBetType();

    @Test
    public void shouldMatchWhenPocketNumberIsOdd() {
        Assert.assertTrue(instance.test(1));
    }

    @Test
    public void shouldNotMatchWhenPocketNumberIsEven() {
        Assert.assertFalse(instance.test(2));
    }


}