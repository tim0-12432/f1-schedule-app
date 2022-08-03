package de.tim0_12432.f1_schedule_app.data.entity.builder;

import de.tim0_12432.f1_schedule_app.data.entity.Constructor;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResult;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultStatus;

public class RaceResultBuilder extends EntityBuilder<RaceResult> {
    private int number;

    private int position;

    private int points;

    private Driver driver;

    private Constructor team;

    private int grid;

    private int laps;

    private String raceTime;

    private RaceResultStatus status;

    private String fastestLapTime;

    private String fastestLapSpeed;

    public RaceResultBuilder() {
        this.number = -1;
        this.position = -1;
        this.points = -1;
        this.driver = null;
        this.team = null;
        this.grid = -1;
        this.laps = -1;
        this.raceTime = null;
        this.status = null;
        this.fastestLapTime = null;
        this.fastestLapSpeed = null;
    }

    public RaceResultBuilder withNumber(int number) {
        this.number = number;
        return this;
    }

    public RaceResultBuilder withPosition(int position) {
        this.position = position;
        return this;
    }

    public RaceResultBuilder withPoints(int points) {
        this.points = points;
        return this;
    }

    public RaceResultBuilder withDriver(Driver driver) {
        this.driver = driver;
        return this;
    }

    public RaceResultBuilder withTeam(Constructor team) {
        this.team = team;
        return this;
    }

    public RaceResultBuilder withGrid(int grid) {
        this.grid = grid;
        return this;
    }

    public RaceResultBuilder withLaps(int laps) {
        this.laps = laps;
        return this;
    }

    public RaceResultBuilder withRaceTime(String raceTime) {
        this.raceTime = raceTime;
        return this;
    }

    public RaceResultBuilder withStatus(RaceResultStatus status) {
        this.status = status;
        return this;
    }

    public RaceResultBuilder withFastestLapTime(String fastestLapTime) {
        this.fastestLapTime = fastestLapTime;
        return this;
    }

    public RaceResultBuilder withFastestLapSpeed(String fastestLapSpeed) {
        this.fastestLapSpeed = fastestLapSpeed;
        return this;
    }

    public RaceResult build() {
        return new RaceResult(number, position, points, driver, team, grid, laps, raceTime, status, fastestLapTime, fastestLapSpeed);
    }
}
