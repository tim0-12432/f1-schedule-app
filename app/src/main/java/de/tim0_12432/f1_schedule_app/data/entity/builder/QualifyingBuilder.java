package de.tim0_12432.f1_schedule_app.data.entity.builder;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Qualifying;
import de.tim0_12432.f1_schedule_app.data.entity.QualifyingResult;

public class QualifyingBuilder extends EntityBuilder<Qualifying> {

    private int season;

    private int round;

    private String name;

    private Circuit circuit;

    private String url;

    private Date date;

    private Time time;

    private List<QualifyingResult> results;

    public QualifyingBuilder() {
        this.season = -1;
        this.round = -1;
        this.name = null;
        this.circuit = null;
        this.url = null;
        this.date = null;
        this.time = null;
        this.results = new ArrayList<>();
    }

    public QualifyingBuilder withSeason(int season) {
        this.season = season;
        return this;
    }

    public QualifyingBuilder withRound(int round) {
        this.round = round;
        return this;
    }

    public QualifyingBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public QualifyingBuilder withCircuit(Circuit circuit) {
        this.circuit = circuit;
        return this;
    }

    public QualifyingBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public QualifyingBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public QualifyingBuilder withTime(Time time) {
        this.time = time;
        return this;
    }

    public QualifyingBuilder withResults(List<QualifyingResult> results) {
        this.results = results;
        return this;
    }

    public QualifyingBuilder withResult(QualifyingResult result) {
        this.results.add(result);
        return this;
    }

    public Qualifying build() {
        return new Qualifying(season, round, name, circuit, url, date, time, results);
    }
}
