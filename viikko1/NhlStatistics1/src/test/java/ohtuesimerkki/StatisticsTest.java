package ohtuesimerkki;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko","EDM",4,12));
            players.add(new Player("Lemieux","PIT",45,54));
            players.add(new Player("Kurri","EDM",37,53));
            players.add(new Player("Yzerman","DET",42,56));
            players.add(new Player("Gretzky","EDM",35,89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchReturnsNullWhenPlayerNotFound() {
        Assert.assertNull(stats.search("NonexistingPlayer"));
    }

    @Test
    public void searchReturnsCorrectPlayer() {
        Player result = stats.search("Yzerman");

        Assert.assertEquals("Yzerman", result.getName());
        Assert.assertEquals("DET", result.getTeam());
    }

    @Test
    public void teamReturnsEmptyListForNonexistingTeam() {
        Assert.assertTrue(stats.team("PietarinPallo").isEmpty());
    }

    @Test
    public void teamReturnsCorrectAmountOfPlayersForTeam() {
        Assert.assertEquals(3, stats.team("EDM").size());
    }

    @Test
    public void topScorersReturnsEmptyListForNegativeAmount() {
        Assert.assertTrue(stats.topScorers(-42).isEmpty());
    }

    @Test
    public void topScorersReturnsCorrectAmountOfPlayers() {
        Assert.assertEquals(4, stats.topScorers(3).size());
    }
}
