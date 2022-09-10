package de.tim0_12432.f1_schedule_app.data.entity.builder;

import de.tim0_12432.f1_schedule_app.data.entity.Constructor;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.QualifyingResult;

public class QualifyingResultBuilder extends EntityBuilder<QualifyingResult> {
    private int number;

    private int position;

    private Driver driver;

    private Constructor team;

    private final String[] lapTimes;

    public QualifyingResultBuilder() {
        this.number = -1;
        this.position = -1;
        this.driver = null;
        this.team = null;
        this.lapTimes = new String[3];
    }

    public QualifyingResultBuilder withNumber(int number) {
        this.number = number;
        return this;
    }

    public QualifyingResultBuilder withPosition(int position) {
        this.position = position;
        return this;
    }

    public QualifyingResultBuilder withDriver(Driver driver) {
        this.driver = driver;
        return this;
    }

    public QualifyingResultBuilder withTeam(Constructor team) {
        this.team = team;
        return this;
    }

    public QualifyingResultBuilder withLapTimes(String... lapTimes) {
        for (int i = 0; i < this.lapTimes.length; i++) {
            if (lapTimes.length >= i) {
                this.lapTimes[i] = lapTimes[i];
            }
        }
        return this;
    }

    public QualifyingResultBuilder withQ1(String lapTime) {
        this.lapTimes[0] = lapTime;
        return this;
    }


    public QualifyingResultBuilder withQ2(String lapTime) {
        this.lapTimes[1] = lapTime;
        return this;
    }


    public QualifyingResultBuilder withQ3(String lapTime) {
        this.lapTimes[2] = lapTime;
        return this;
    }

    public QualifyingResult build() {
        return new QualifyingResult(number, position, driver, team, lapTimes);
    }
}
