package io.mstream.roulette.domain.roulette.bet;

import org.junit.Assert;
import org.junit.Test;


public class NumberBetTest {

    private NumberBet instance = new NumberBet(2);

    @Test
    public void shouldMatchWhenPocketNumberIsChosenNumber() {
        Assert.assertTrue(instance.test(2));
    }

    @Test
    public void shouldNotMatchWhenPocketNumberIsNotChosenNumber() {
        Assert.assertFalse(instance.test(3));
    }


}