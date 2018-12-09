package edu.infnet.al.izi_quiz.Assets.FirebaseData;

public class Votes {
    public long animal;
    public long tv;
    public long world;

    public long getAnimal() {
        return animal;
    }

    public void setAnimal(long animal) {
        this.animal = animal;
    }

    public long getTv() {
        return tv;
    }

    public void setTv(long tv) {
        this.tv = tv;
    }

    public long getWorld() {
        return world;
    }

    public void setWorld(long world) {
        this.world = world;
    }

    public Votes(long animal, long tv, long world){
        this.animal = animal;
        this.world = world;
        this.tv = tv;
    }
}