package edu.infnet.al.izi_quiz.Assets.PlayersList;

public class Player {

    String name;
    int points;
    int pwrUpShuffle;
    int pwrUpFadeIn;


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPwrUpShuffle() {
        return pwrUpShuffle;
    }

    public void setPwrUpShuffle(int pwrUpShuffle) {
        this.pwrUpShuffle = pwrUpShuffle;
    }

    public int getPwrUpFadeIn() {
        return pwrUpFadeIn;
    }

    public void setPwrUpFadeIn(int pwrUpFadeIn) {
        this.pwrUpFadeIn = pwrUpFadeIn;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(String name, int points, int pwrUpShuffle, int pwrUpFadeIn) {
        this.name = name;
        this.points = points;
        this.pwrUpShuffle = pwrUpShuffle;
        this.pwrUpFadeIn = pwrUpFadeIn;
    }
}
