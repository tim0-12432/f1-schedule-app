package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.NonNull;

import java.util.List;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;

public class RaceResultList {
    private static final String speedUnit = MainActivity.getAppResources().getString(R.string.unit_kph);

    private final List<RaceResult> results;

    public RaceResultList(List<RaceResult> results) {
        this.results = results;
    }

    public List<RaceResult> getResults() {
        return results;
    }

    @NonNull
    @Override
    public String toString() {
        return "RaceResultList{" +
                "results=" + results.toString() +
                '}';
    }
}
