package io.mstream.roulette.parsing;

import io.mstream.roulette.domain.Player;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PlayerLineStringParserTest {

    private PlayerLineStringParser instance = new PlayerLineStringParser();

    @Test
    public void shouldCreatePlayerFromNameOnly() {
        String playerLine = "John";
        //
        Player parsedPlayer = instance.apply(playerLine);
        //
        assertNotNull(parsedPlayer);
        assertEquals("John", parsedPlayer.getName());
        assertNotNull(parsedPlayer.getTotalWin());
        assertEquals(BigDecimal.ZERO.doubleValue(), parsedPlayer.getTotalWin().doubleValue(), 0);
        assertNotNull(parsedPlayer.getTotalBet());
        assertEquals(BigDecimal.ZERO.doubleValue(), parsedPlayer.getTotalBet().doubleValue(), 0);
    }

    @Test
    public void shouldCreatePlayerFromNameAndTotalWinAndTotalBet() {
        String playerLine = "John,9.5,10.0";
        //
        Player parsedPlayer = instance.apply(playerLine);
        //
        assertNotNull(parsedPlayer);
        assertEquals("John", parsedPlayer.getName());
        assertNotNull(parsedPlayer.getTotalWin());
        assertEquals(BigDecimal.valueOf(9.5).doubleValue(), parsedPlayer.getTotalWin().doubleValue(), 0);
        assertNotNull(parsedPlayer.getTotalBet());
        assertEquals(BigDecimal.valueOf(10.0).doubleValue(), parsedPlayer.getTotalBet().doubleValue(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnNullString() {
        String playerLine = null;
        //
        instance.apply(playerLine);
        //
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnEmptyString() {
        String playerLine = "";
        //
        instance.apply(playerLine);
        //
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnPropertiesMismatchString() {
        String playerLine = "John,John";
        //
        instance.apply(playerLine);
        //
    }
}