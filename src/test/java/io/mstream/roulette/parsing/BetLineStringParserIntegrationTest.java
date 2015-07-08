package io.mstream.roulette.parsing;

import io.mstream.roulette.command.Bet;
import io.mstream.roulette.command.Player;
import io.mstream.roulette.domain.roulette.bet.BetType;
import io.mstream.roulette.domain.roulette.bet.BetTypeFactory;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BetLineStringParserIntegrationTest {

    private BetLineStringParser instance = new BetLineStringParser(new BetTypeFactory());

    @Test
    public void shouldCreatePlayerFromNameOnly() {
        String betLine = "John EVEN 2.0";
        //
        Bet parsedBet = instance.apply(betLine);
        //
        assertNotNull(parsedBet);
        assertEquals("John", parsedBet.getPlayerName());
        assertNotNull(parsedBet.getType());
        assertEquals(BetType., parsedBet.getTotalWin());
        assertNotNull(parsedBet.getTotalBet());
        assertEquals(BigDecimal.ZERO, parsedBet.getTotalBet());
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