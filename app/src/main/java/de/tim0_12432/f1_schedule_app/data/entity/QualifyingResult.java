package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class QualifyingResult extends AbstractEntity {
    private final int number;

    private final int position;

    private final Driver driver;

    private final Constructor team;

    private final String[] lapTimes;

    public QualifyingResult(int number, int position, Driver driver, Constructor team) {
        this.number = number;
        this.position = position;
        this.driver = driver;
        this.team = team;
        this.lapTimes = new String[3];
    }

    public QualifyingResult(int number, int position, Driver driver, Constructor team, String... lapTimes) {
        this(number, position, driver, team);
        for (int i = 0; i < this.lapTimes.length; i++) {
            if (lapTimes.length >= i) {
                this.lapTimes[i] = lapTimes[i];
            }
        }
    }

    public int getNumber() {
        return number;
    }

    public int getPosition() {
        return position;
    }

    public Driver getDriver() {
        return driver;
    }

    public Constructor getTeam() {
        return team;
    }

    public String[] getLapTimes() {
        return lapTimes;
    }

    public String getFastestLapTime() {
        if (getQualifyingRound() > 0) {
            return lapTimes[getQualifyingRound() - 1];
        } else {
            return null;
        }
    }

    public int getQualifyingRound() {
        if (lapTimes[2] != null) {
            return 3;
        } else if (lapTimes[1] != null) {
            return 2;
        } else if (lapTimes[0] != null) {
            return 1;
        } else {
            return 0;
        }
    }

    public static QualifyingResult copyOf(QualifyingResult r) {
        return new QualifyingResult(r.getNumber(), r.getPosition(), r.getDriver(), r.getTeam(), r.getLapTimes());
    }

    @NonNull
    @Override
    public String toString() {
        return "QualifyingResult{" +
                "number=" + number +
                ", position=" + position +
                ", driver=" + driver +
                ", team=" + team +
                ", times=" + Arrays.toString(lapTimes) +
                '}';
    }
}
