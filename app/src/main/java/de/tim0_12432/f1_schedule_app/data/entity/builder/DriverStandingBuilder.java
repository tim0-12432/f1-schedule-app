package de.tim0_12432.f1_schedule_app.data.entity.builder;

import de.tim0_12432.f1_schedule_app.data.entity.Constructor;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;

public class DriverStandingBuilder extends EntityBuilder<DriverStanding> {

    private int position;

    private int points;

    private int wins;

    private Driver driver;

    private Constructor constructor;

    public DriverStandingBuilder() {
        this.position = 0;
        this.points = 0;
        this.wins = 0;
        this.driver = null;
        this.constructor = null;
    }

    public DriverStandingBuilder withPosition(int position) {
        this.position = position;
        return this;
    }

    public DriverStandingBuilder withPoints(int points) {
        this.points = points;
        return this;
    }

    public DriverStandingBuilder withWins(int wins) {
        this.wins = wins;
        return this;
    }

    public DriverStandingBuilder withDriver(Driver driver) {
        this.driver = driver;
        return this;
    }

    public DriverStandingBuilder withConstructor(Constructor constructor) {
        this.constructor = constructor;
        return this;
    }

    public DriverStanding build() {
        return new DriverStanding(position, points, wins, driver, constructor);
    }
}
