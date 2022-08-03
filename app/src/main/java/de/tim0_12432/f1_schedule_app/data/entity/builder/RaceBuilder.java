package de.tim0_12432.f1_schedule_app.data.entity.builder;

import java.sql.Date;
import java.sql.Time;

import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultList;

public class RaceBuilder extends EntityBuilder<Race> {

    private int season;

    private int round;

    private String name;

    private Circuit circuit;

    private String url;

    private Date date;

    private Time time;

    private RaceResultList results;

    public RaceBuilder() {
        this.season = -1;
        this.round = -1;
        this.name = null;
        this.circuit = null;
        this.url = null;
        this.date = null;
        this.time = null;
        this.results = null;
    }

    public RaceBuilder withSeason(int season) {
        this.season = season;
        return this;
    }

    public RaceBuilder withRound(int round) {
        this.round = round;
        return this;
    }

    public RaceBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RaceBuilder withCircuit(Circuit circuit) {
        this.circuit = circuit;
        return this;
    }

    public RaceBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public RaceBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public RaceBuilder withTime(Time time) {
        this.time = time;
        return this;
    }

    public RaceBuilder withResults(RaceResultList results) {
        this.results = results;
        return this;
    }

    public Race build() {
        if (results == null)
            return new Race(season, round, name, circuit, url, date, time);
        return new Race(season, round, name, circuit, url, date, time, results);
    }
}
