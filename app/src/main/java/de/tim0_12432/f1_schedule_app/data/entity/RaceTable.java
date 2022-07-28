package de.tim0_12432.f1_schedule_app.data.entity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name="MRData")
public class RaceTable {

    @ElementList(name="RaceTable")
    private final List<Race> list;

    public RaceTable(@ElementList(name="RaceTable") List<Race> list) {
        this.list = list;
    }

    public RaceTable() {
        list = new ArrayList<>();
    }

    public List<Race> getList() {
        return list;
    }
}
