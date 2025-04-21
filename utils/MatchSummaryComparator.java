package utils;

import model.Match;

import java.util.Comparator;

public class MatchSummaryComparator implements Comparator<Match> {
    @Override
    public int compare(Match m1, Match m2) {
        int scoreDiff = Integer.compare(m2.getTotalScore(), m1.getTotalScore());
        if (scoreDiff != 0) return scoreDiff;
        return m2.getStartTime().compareTo(m1.getStartTime());
    }
}
