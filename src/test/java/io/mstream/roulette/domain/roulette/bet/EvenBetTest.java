package io.mstream.roulette.domain.roulette.bet;

import org.junit.Assert;
import org.junit.Test;


public class EvenBetTest {

    private EvenBet instance = new EvenBet();

    @Test
    public void shouldMatchWhenPocketNumberIsEven() {
        Assert.assertTrue(instance.test(2));
    }

    @Test
    public void shouldNotMatchWhenPocketNumberIsOdd() {
        Assert.assertFalse(instance.test(3));
    }


}