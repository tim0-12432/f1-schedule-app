package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.NonNull;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.utility.DateTime;

public class Qualifying extends AbstractEntity {
    private final int season;

    private final int round;

    private final String name;

    private final Circuit circuit;

    private final String url;

    private final Date date;

    private final Time time;

    private List<QualifyingResult> results = null;

    public Qualifying(int season, int round, String name, Circuit circuit, String url, String date, String time, List<QualifyingResult> results) {
        this(season, round, name, circuit, url, Date.valueOf(date), Time.valueOf(time));
        this.results = results;
    }

    public Qualifying(int season, int round, String name, Circuit circuit, String url, Date date, Time time, List<QualifyingResult> results) {
        this(season, round, name, circuit, url, date, time);
        this.results = results;
    }

    public Qualifying(int season, int round, String name, Circuit circuit, String url, String date, String time) {
        this(season, round, name, circuit, url, Date.valueOf(date), Time.valueOf(time));
    }

    public Qualifying(int season, int round, String name, Circuit circuit, String url, Date date, Time time) {
        this.season = season;
        this.round = round;
        this.name = name;
        this.circuit = circuit;
        this.url = url;
        this.date = DateTime.plusDays(date, 2);
        this.time = time;
        this.results = new ArrayList<>();
    }

    public int getSeason() {
        return season;
    }

    public int getRound() {
        return round;
    }

    public String getName() {
        return name;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public String getUrl() {
        return url;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public List<QualifyingResult> getResults() {
        return results;
    }

    public void addResult(QualifyingResult result) {
        this.results.add(result);
    }

    public static Qualifying copyOf(Qualifying r) {
        return new Qualifying(r.getSeason(), r.getRound(), r.getName(), r.getCircuit(), r.getUrl(), r.getDate(), r.getTime(), r.getResults());
    }

    @NonNull
    @Override
    public String toString() {
        return "Qualifying{" +
                "season=" + season +
                ", round=" + round +
                ", name='" + name + '\'' +
                ", circuit=" + circuit +
                ", url='" + url + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", results=" + results.toString() +
                '}';
    }
}
