package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.NonNull;

public class RaceResult extends AbstractEntity {

    private final int number;

    private final int position;

    private final int points;

    private final Driver driver;

    private final Constructor team;

    private final int grid;

    private final int laps;

    private final String raceTime;

    private final RaceResultStatus status;

    private final String fastestLapTime;

    private final String fastestLapSpeed;

    public RaceResult(int number, int position, int points, Driver driver, Constructor team, int grid,
                      int laps, String raceTime, RaceResultStatus status, String fastestLapTime, String fastestLapSpeed) {
        this.number = number;
        this.position = position;
        this.points = points;
        this.driver = driver;
        this.team = team;
        this.grid = grid;
        this.laps = laps;
        this.raceTime = raceTime;
        this.status = status;
        this.fastestLapTime = fastestLapTime;
        this.fastestLapSpeed = fastestLapSpeed;
    }

    public int getNumber() {
        return number;
    }

    public int getPosition() {
        return position;
    }

    public int getPoints() {
        return points;
    }

    public Driver getDriver() {
        return driver;
    }

    public Constructor getTeam() {
        return team;
    }

    public int getGrid() {
        return grid;
    }

    public int getLaps() {
        return laps;
    }

    public String getRaceTime() {
        return raceTime;
    }

    public RaceResultStatus getStatus() {
        return status;
    }

    public String getFastestLapTime() {
        return fastestLapTime;
    }

    public String getFastestLapSpeed() {
        return fastestLapSpeed;
    }

    public static RaceResult copyOf(RaceResult r) {
        return new RaceResult(r.getNumber(), r.getPosition(), r.getPoints(), r.getDriver(), r.getTeam(),
                r.getGrid(), r.getLaps(), r.getRaceTime(), r.getStatus(), r.getFastestLapTime(), r.getFastestLapSpeed());
    }

    @NonNull
    @Override
    public String toString() {
        return "RaceResult{" +
                "number=" + number +
                ", position=" + position +
                ", points=" + points +
                ", driver=" + driver +
                ", team=" + team +
                ", grid=" + grid +
                ", laps=" + laps +
                ", raceTime='" + raceTime + '\'' +
                ", status=" + status +
                ", fastestLapTime='" + fastestLapTime + '\'' +
                ", fastestLapSpeed='" + fastestLapSpeed + '\'' +
                '}';
    }
}
