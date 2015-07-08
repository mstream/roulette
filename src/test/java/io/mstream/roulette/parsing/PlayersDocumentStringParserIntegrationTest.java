package io.mstream.roulette.parsing;

import io.mstream.roulette.command.Player;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class PlayersDocumentStringParserIntegrationTest {

    private PlayersDocumentParser instance =
            new PlayersDocumentParser(new PlayerLineStringParser());

    @Test
    public void test() {
        Player john = new Player.Builder("John")
                .build();
        Player mike = new Player.Builder("Mike")
                .build();
        Player doug = new Player.Builder("Doug")
                .withTotalWin(BigDecimal.ONE)
                .withTotalBet(BigDecimal.TEN)
                .build();
        String playersStr = "John\nMike\nDoug,1,10";
        //
        List<Player> players =
                instance.apply(playersStr);
        //
        Assert.assertNotNull(players);
        Assert.assertEquals(3, players.size());
        Assert.assertEquals(john, players.get(0));
        Assert.assertEquals(mike, players.get(1));
        Assert.assertEquals(doug, players.get(2));

    }
}