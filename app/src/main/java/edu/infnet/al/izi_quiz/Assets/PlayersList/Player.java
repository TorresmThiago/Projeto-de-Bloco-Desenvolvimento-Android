package edu.infnet.al.izi_quiz.Assets.PlayersList;

public class Player {

    public String key;
    public String name;
    public long points;
    public long pwrUpScramble;
    public long pwrUpFadeIn;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public long getPwrUpScramble() {
        return pwrUpScramble;
    }

    public void setPwrUpScramble(long pwrUpScramble) {
        this.pwrUpScramble = pwrUpScramble;
    }

    public long getPwrUpFadeIn() {
        return pwrUpFadeIn;
    }

    public void setPwrUpFadeIn(long pwrUpFadeIn) {
        this.pwrUpFadeIn = pwrUpFadeIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(String name, long points, long pwrUpScramble, long pwrUpFadeIn, String key) {
        this.key = key;
        this.name = name;
        this.points = points;
        this.pwrUpFadeIn = pwrUpFadeIn;
        this.pwrUpScramble = pwrUpScramble;
    }
}
