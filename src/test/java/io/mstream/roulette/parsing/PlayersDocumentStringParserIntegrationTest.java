package io.mstream.roulette.parsing;

import io.mstream.roulette.command.RegisterPlayer;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class PlayersDocumentStringParserIntegrationTest {

    private PlayersDocumentParser instance =
            new PlayersDocumentParser(new PlayerLineStringParser());

    @Test
    public void test() {
        RegisterPlayer john = new RegisterPlayer.Builder("John")
                .build();
        RegisterPlayer mike = new RegisterPlayer.Builder("Mike")
                .build();
        RegisterPlayer doug = new RegisterPlayer.Builder("Doug")
                .withTotalWin(BigDecimal.ONE)
                .withTotalBet(BigDecimal.TEN)
                .build();
        String playersStr = "John\nMike\nDoug,1,10";
        //
        List<RegisterPlayer> registerPlayers =
                instance.apply(playersStr);
        //
        Assert.assertNotNull(registerPlayers);
        Assert.assertEquals(3, registerPlayers.size());
        Assert.assertEquals(john, registerPlayers.get(0));
        Assert.assertEquals(mike, registerPlayers.get(1));
        Assert.assertEquals(doug, registerPlayers.get(2));

    }
}