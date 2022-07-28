package de.tim0_12432.f1_schedule_app.data.entity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "StandingsTable")
public class StandingsTable {

    @ElementList(name = "StandingsList")
    private final List<DriverStanding> list;

    @Attribute(name = "season")
    private final int season;

    public StandingsTable(int season) {
        list = new ArrayList<>();
        this.season = season;
    }

    public List<DriverStanding> getList() {
        return list;
    }

    public int getSeason() {
        return season;
    }
}
