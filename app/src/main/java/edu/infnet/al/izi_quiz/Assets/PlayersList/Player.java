package edu.infnet.al.izi_quiz.Assets.PlayersList;

public class Player {

    String name;
    long points;
    long pwrUpShuffle;
    long pwrUpFadeIn;


    public long getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public long getPwrUpShuffle() {
        return pwrUpShuffle;
    }

    public void setPwrUpShuffle(int pwrUpShuffle) {
        this.pwrUpShuffle = pwrUpShuffle;
    }

    public long getPwrUpFadeIn() {
        return pwrUpFadeIn;
    }

    public void setPwrUpFadeIn(int pwrUpFadeIn) {
        this.pwrUpFadeIn = pwrUpFadeIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(String name, long points, long pwrUpShuffle, long pwrUpFadeIn) {
        this.name = name;
        this.points = points;
        this.pwrUpShuffle = pwrUpShuffle;
        this.pwrUpFadeIn = pwrUpFadeIn;
    }
}
