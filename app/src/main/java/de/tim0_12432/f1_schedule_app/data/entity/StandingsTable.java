package de.tim0_12432.f1_schedule_app.data.entity;

import java.util.ArrayList;
import java.util.List;

public class StandingsTable {

    private final List<DriverStanding> list;

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
