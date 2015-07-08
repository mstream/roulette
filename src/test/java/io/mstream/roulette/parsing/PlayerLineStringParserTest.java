package io.mstream.roulette.parsing;

import io.mstream.roulette.command.RegisterPlayer;
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
        RegisterPlayer parsedPlayer = instance.apply(playerLine);
        //
        assertNotNull(parsedPlayer);
        assertEquals("John", parsedPlayer.getName());
        assertNotNull(parsedPlayer.getTotalWin());
        assertEquals(BigDecimal.ZERO, parsedPlayer.getTotalWin());
        assertNotNull(parsedPlayer.getTotalBet());
        assertEquals(BigDecimal.ZERO, parsedPlayer.getTotalBet());
    }

    @Test
    public void shouldCreatePlayerFromNameAndTotalWinAndTotalBet() {
        String playerLine = "John,9.5,10.0";
        //
        RegisterPlayer parsedPlayer = instance.apply(playerLine);
        //
        assertNotNull(parsedPlayer);
        assertEquals("John", parsedPlayer.getName());
        assertNotNull(parsedPlayer.getTotalWin());
        assertEquals(BigDecimal.valueOf(9.5), parsedPlayer.getTotalWin());
        assertNotNull(parsedPlayer.getTotalBet());
        assertEquals(BigDecimal.valueOf(10.0), parsedPlayer.getTotalBet());
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