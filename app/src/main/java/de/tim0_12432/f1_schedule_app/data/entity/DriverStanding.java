package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.NonNull;

public class DriverStanding extends Entity {
    private final int position;
    private final int points;
    private final int wins;
    private final Driver driver;
    private final Constructor constructor;

    public DriverStanding(int position, int points, int wins, Driver driver, Constructor constructor) {
        this.position = position;
        this.points = points;
        this.wins = wins;
        this.driver = driver;
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

    public Driver getDriver() {
        return driver;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public static DriverStanding copyOf(DriverStanding d) {
        return new DriverStanding(d.getPosition(), d.getPoints(), d.getWins(), d.getDriver(), d.getConstructor());
    }

    @NonNull
    @Override
    public String toString() {
        return "DriverStanding{" +
                "position=" + position +
                ", points=" + points +
                ", wins=" + wins +
                ", driver=" + driver +
                ", constructor=" + constructor +
                '}';
    }
}
