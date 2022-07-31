package de.tim0_12432.f1_schedule_app.data.entity;

import java.util.ArrayList;
import java.util.List;

public class DriverTable {

    private final List<Driver> list;

    private final int season;

    public DriverTable(int season) {
        list = new ArrayList<>();
        this.season = season;
    }

    public List<Driver> getList() {
        return list;
    }

    public int getSeason() {
        return season;
    }
}
