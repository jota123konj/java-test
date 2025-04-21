package test;

import model.Match;
import service.ScoreBoard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {

    @Test
    public void testStartAndGetSummary() {
        ScoreBoard board = new ScoreBoard();
        board.startMatch("Team A", "Team B");

        List<Match> matches = board.getSummary();
        assertEquals(1, matches.size());
        assertEquals("Team A", matches.get(0).getHomeTeam());
    }

    @Test
    public void testUpdateScore() {
        ScoreBoard board = new ScoreBoard();
        board.startMatch("A", "B");
        board.updateScore("A", "B", 2, 1);

        Match match = board.getSummary().get(0);
        assertEquals(3, match.getTotalScore());
    }

    @Test
    public void testFinishMatch() {
        ScoreBoard board = new ScoreBoard();
        board.startMatch("A", "B");
        board.finishMatch("A", "B");

        assertTrue(board.getSummary().isEmpty());
    }

    @Test
    public void testSummaryOrdering() throws InterruptedException {
        ScoreBoard board = new ScoreBoard();
        board.startMatch("Team A", "Team B");
        Thread.sleep(10);
        board.startMatch("Team C", "Team D");

        board.updateScore("Team A", "Team B", 1, 0);
        board.updateScore("Team C", "Team D", 1, 0);

        List<Match> summary = board.getSummary();
        assertEquals("Team C", summary.get(0).getHomeTeam()); // more recent one
    }
}
