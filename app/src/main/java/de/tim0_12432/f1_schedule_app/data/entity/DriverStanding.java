package de.tim0_12432.f1_schedule_app.data.entity;

public class DriverStanding {
    private int position;
    private int points;
    private int wins;
    private Driver driver;
    private Constructor constructor;

    public DriverStanding() {}

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

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    public static DriverStanding copyOf(DriverStanding d) {
        return new DriverStanding(d.getPosition(), d.getPoints(), d.getWins(), d.getDriver(), d.getConstructor());
    }
}
