package edu.infnet.al.izi_quiz.Assets.MatchPlayed;

public class MatchPlayed {

    private String matchDate;

    public String getMatchDate() {
        return matchDate;
    }

    public int getMatchPosition() {
        return matchPosition;
    }

    private int matchPosition;

    public MatchPlayed(String matchDate, int matchPosition) {
        this.matchDate = matchDate;
        this.matchPosition = matchPosition;
    }

}
