package edu.infnet.al.izi_quiz.Assets.PlayersList;

public class Player {

    String name;
    long points;
    long pwrUpScramble;
    long pwrUpFadeIn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(String name, long points, long pwrUpScramble, long pwrUpFadeIn) {
        this.name = name;
        this.points = points;
        this.pwrUpScramble = pwrUpScramble;
        this.pwrUpFadeIn = pwrUpFadeIn;
    }
}
