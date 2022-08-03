package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.NonNull;

public class ConstructorStanding extends Entity {
    private final int position;
    private final int points;
    private final int wins;
    private final Constructor constructor;

    public ConstructorStanding(int position, int points, int wins, Constructor constructor) {
        this.position = position;
        this.points = points;
        this.wins = wins;
        this.constructor = constructor;
    }

    public int getPosition() {
        return position;
    }

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public static ConstructorStanding copyOf(ConstructorStanding d) {
        return new ConstructorStanding(d.getPosition(), d.getPoints(), d.getWins(), d.getConstructor());
    }

    @NonNull
    @Override
    public String toString() {
        return "ConstructorStanding{" +
                "position=" + position +
                ", points=" + points +
                ", wins=" + wins +
                ", constructor=" + constructor +
                '}';
    }
}
