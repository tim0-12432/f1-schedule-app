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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getFastestLapTime() {
        SimpleDateFormat parser = new SimpleDateFormat("mm:ss.SSS");
        List<Date> lapTimeList = new ArrayList<>();
        for (String lapTime : lapTimes) {
            if (lapTime != null && !lapTime.isEmpty()) {
                try {
                    lapTimeList.add(parser.parse(lapTime));
                } catch (ParseException e) {
                    Logger.log(Logger.LogLevel.ERROR, "'" + lapTime + "' could not be parsed!");
                }
            }
        }
        lapTimeList.sort(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        });

        Date fastest = lapTimeList.get(0);
        return parser.format(fastest);
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
