package service;

import model.Match;
import utils.MatchSummaryComparator;

import java.util.*;

public class ScoreBoard {
    private final Map<String, Match> matchesInProgress = new HashMap<>();

    public void startMatch(String homeTeam, String awayTeam) {
        String key = getKey(homeTeam, awayTeam);
        if (matchesInProgress.containsKey(key)) {
            throw new IllegalArgumentException("Match already exists");
        }
        matchesInProgress.put(key, new Match(homeTeam, awayTeam));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        String key = getKey(homeTeam, awayTeam);
        Match match = matchesInProgress.get(key);
        if (match == null) {
            throw new NoSuchElementException("Match not found");
        }
        match.updateScore(homeScore, awayScore);
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        String key = getKey(homeTeam, awayTeam);
        if (!matchesInProgress.containsKey(key)) {
            throw new NoSuchElementException("Match not found");
        }
        matchesInProgress.remove(key);
    }

    public List<Match> getSummary() {
        List<Match> summary = new ArrayList<>(matchesInProgress.values());
        summary.sort(new MatchSummaryComparator());
        return summary;
    }

    private String getKey(String home, String away) {
        return home + " vs " + away;
    }
}
