package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.NonNull;

import java.sql.Date;
import java.sql.Time;

import de.tim0_12432.f1_schedule_app.utility.DateTime;

public class Race extends AbstractEntity {

    private final int season;

    private final int round;

    private final String name;

    private final Circuit circuit;

    private final String url;

    private final Date date;

    private final Time time;

    private RaceResultList results = null;

    public Race(int season, int round, String name, Circuit circuit, String url, String date, String time, RaceResultList results) {
        this(season, round, name, circuit, url, Date.valueOf(date), Time.valueOf(time));
        this.results = results;
    }

    public Race(int season, int round, String name, Circuit circuit, String url, Date date, Time time, RaceResultList results) {
        this(season, round, name, circuit, url, date, time);
        this.results = results;
    }

    public Race(int season, int round, String name, Circuit circuit, String url, String date, String time) {
        this(season, round, name, circuit, url, Date.valueOf(date), Time.valueOf(time));
    }

    public Race(int season, int round, String name, Circuit circuit, String url, Date date, Time time) {
        this.season = season;
        this.round = round;
        this.name = name;
        this.circuit = circuit;
        this.url = url;
        this.date = DateTime.plusDays(date, 2);
        this.time = time;
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

    public RaceResultList getResults() {
        return results;
    }

    public void addResults(RaceResultList results) {
        this.results = results;
    }

    public static Race copyOf(Race r) {
        if (r.getResults() == null)
            return new Race(r.getSeason(), r.getRound(), r.getName(), r.getCircuit(), r.getUrl(), r.getDate(), r.getTime());
        return new Race(r.getSeason(), r.getRound(), r.getName(), r.getCircuit(), r.getUrl(), r.getDate(), r.getTime(), r.getResults());
    }

    @NonNull
    @Override
    public String toString() {
        return "Race{" +
                "season=" + season +
                ", round=" + round +
                ", name='" + name + '\'' +
                ", circuit=" + circuit +
                ", url='" + url + '\'' +
                ", date=" + date +
                ", time=" + time +
                (results != null ? ", results=" + results : "") +
                '}';
    }
}
