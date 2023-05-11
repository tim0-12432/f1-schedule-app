package de.tim0_12432.f1_schedule_app.data.entity;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.tim0_12432.f1_schedule_app.utility.Logger;

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
        List<String> lapTimeList = new ArrayList<>();
        for (String lapTime : lapTimes) {
            if (lapTime != null && !lapTime.isEmpty()) {
                lapTimeList.add(lapTime);
            }
        }
        lapTimeList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.equals(o2)) {
                    return 0;
                } else {
                    SimpleDateFormat parser = new SimpleDateFormat("mm:ss.SSS", Locale.ROOT);
                    try {
                        Date date1 = parser.parse(o1);
                        Date date2 = parser.parse(o2);
                        return date1.compareTo(date2);
                    } catch (ParseException e) {
                        Logger.log(Logger.LogLevel.ERROR, "'" + o1 + "' or '" + o2 + "' could not be parsed!");
                        return 0;
                    }
                }
            }
        });

        if (lapTimeList.isEmpty()) {
            return null;
        }
        return lapTimeList.get(0);
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
