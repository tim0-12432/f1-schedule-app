package de.tim0_12432.f1_schedule_app.data.entity.builder;

import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.RaceResult;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultList;

public class RaceResultListBuilder {

    private List<RaceResult> raceResultList;

    public RaceResultListBuilder() {
        this.raceResultList = new ArrayList<>();
    }

    public RaceResultListBuilder withRaceResult(RaceResult raceResult) {
        this.raceResultList.add(raceResult);
        return this;
    }

    public RaceResultList build() {
        return new RaceResultList(this.raceResultList);
    }
}
