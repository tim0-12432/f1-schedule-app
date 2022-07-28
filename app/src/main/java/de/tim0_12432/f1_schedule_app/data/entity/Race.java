package de.tim0_12432.f1_schedule_app.data.entity;

import android.annotation.SuppressLint;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.time.LocalDate;
import java.time.LocalTime;

import de.tim0_12432.f1_schedule_app.data.entity.practice.FirstPractice;
import de.tim0_12432.f1_schedule_app.data.entity.practice.Qualifying;
import de.tim0_12432.f1_schedule_app.data.entity.practice.SecondPractice;
import de.tim0_12432.f1_schedule_app.data.entity.practice.Sprint;
import de.tim0_12432.f1_schedule_app.data.entity.practice.ThirdPractice;

@Root(name="Race")
public class Race {

    @Attribute(name="season")
    private int season;

    @Attribute(name="round")
    private int round;

    @Element(name="RaceName")
    private String name;

    @Element(name="Circuit")
    private Circuit circuit;

    @Attribute(name="url")
    private String url;

    @Element(name="Date")
    private LocalDate date;

    @Element(name="Time")
    private LocalTime time;

    @Element(name="FirstPractice")
    private FirstPractice firstPractice;

    @Element(name="SecondPractice")
    private SecondPractice secondPractice;

    @Element(name="ThirdPractice", required=false)
    private ThirdPractice thirdPractice;

    @Element(name="Qualifying")
    private Qualifying qualifying;

    @Element(name="Sprint", required=false)
    private Sprint sprint;

    @SuppressLint("NewApi")
    public Race(@Attribute(name="season") int season, @Attribute(name="round") int round, @Element(name="RaceName") String name,
                @Element(name="Circuit") Circuit circuit, @Attribute(name="url") String url, @Element(name="Date") String date,
                @Element(name="Time") String time, @Element(name="FirstPractice") FirstPractice firstPractice,
                @Element(name="SecondPractice") SecondPractice secondPractice,
                @Element(name="ThirdPractice", required=false) ThirdPractice thirdPractice, @Element(name="Qualifying") Qualifying qualifying) {
        new Race(season, round, name, circuit, url, LocalDate.parse(date), LocalTime.parse(time),
                firstPractice, secondPractice, thirdPractice, qualifying);
    }

    @SuppressLint("NewApi")
    public Race(@Attribute(name="season") int season, @Attribute(name="round") int round, @Element(name="RaceName") String name,
                @Element(name="Circuit") Circuit circuit, @Attribute(name="url") String url, @Element(name="Date") String date,
                @Element(name="Time") String time, @Element(name="FirstPractice") FirstPractice firstPractice,
                @Element(name="SecondPractice") SecondPractice secondPractice, @Element(name="Qualifying") Qualifying qualifying,
                @Element(name="Sprint", required=false) Sprint sprint) {
        new Race(season, round, name, circuit, url, LocalDate.parse(date), LocalTime.parse(time),
                firstPractice, secondPractice, qualifying, sprint);
    }

    public Race(@Attribute(name="season") int season, @Attribute(name="round") int round, @Element(name="RaceName") String name,
                @Element(name="Circuit") Circuit circuit, @Attribute(name="url") String url, @Element(name="Date") LocalDate date,
                @Element(name="Time") LocalTime time, @Element(name="FirstPractice") FirstPractice firstPractice,
                @Element(name="SecondPractice") SecondPractice secondPractice, @Element(name="ThirdPractice", required=false) ThirdPractice thirdPractice,
                @Element(name="Qualifying") Qualifying qualifying) {
        this.thirdPractice = thirdPractice;
        new Race(season, round, name, circuit, url, date, time, firstPractice, secondPractice, qualifying);
    }

    public Race(@Attribute(name="season") int season, @Attribute(name="round") int round, @Element(name="RaceName") String name,
                @Element(name="Circuit") Circuit circuit, @Attribute(name="url") String url, @Element(name="Date") LocalDate date,
                @Element(name="Time") LocalTime time, @Element(name="FirstPractice") FirstPractice firstPractice,
                @Element(name="SecondPractice") SecondPractice secondPractice, @Element(name="Qualifying") Qualifying qualifying,
                @Element(name="Sprint", required=false) Sprint sprint) {
        this.sprint = sprint;
        new Race(season, round, name, circuit, url, date, time, firstPractice, secondPractice, qualifying);
    }

    protected Race(int season, int round, String name, Circuit circuit, String url, LocalDate date, LocalTime time,
                FirstPractice firstPractice, SecondPractice secondPractice, Qualifying qualifying) {
        this.season = season;
        this.round = round;
        this.name = name;
        this.circuit = circuit;
        this.url = url;
        this.date = date;
        this.time = time;
        this.firstPractice = firstPractice;
        this.secondPractice = secondPractice;
        this.qualifying = qualifying;
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

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public FirstPractice getFirstPractice() {
        return firstPractice;
    }

    public SecondPractice getSecondPractice() {
        return secondPractice;
    }

    public ThirdPractice getThirdPractice() {
        return thirdPractice;
    }

    public Qualifying getQualifying() {
        return qualifying;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public static Race copyOf(Race r) {
        if (r.getThirdPractice() != null)
            return new Race(r.getSeason(), r.getRound(), r.getName(), r.getCircuit(), r.getUrl(), r.getDate(), r.getTime(),
                    r.getFirstPractice(), r.getSecondPractice(), r.getThirdPractice(), r.getQualifying());
        return new Race(r.getSeason(), r.getRound(), r.getName(), r.getCircuit(), r.getUrl(), r.getDate(), r.getTime(),
                r.getFirstPractice(), r.getSecondPractice(), r.getQualifying(), r.getSprint());
    }
}
