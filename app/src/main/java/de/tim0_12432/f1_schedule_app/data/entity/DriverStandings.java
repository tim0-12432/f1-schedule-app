package de.tim0_12432.f1_schedule_app.data.entity;

import java.io.Serializable;

public class DriverStandings implements Serializable {

    private final StandingsTable table;

    public DriverStandings(StandingsTable table) {
        this.table = table;
    }

    public StandingsTable getTable() {
        return table;
    }
}

