package de.tim0_12432.f1_schedule_app.data.entity;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="MRData")
public class DriverStandings {

    @ElementList(name="StandingsTable")
    private final StandingsTable table;

    public DriverStandings(StandingsTable table) {
        this.table = table;
    }

    public StandingsTable getTable() {
        return table;
    }
}

