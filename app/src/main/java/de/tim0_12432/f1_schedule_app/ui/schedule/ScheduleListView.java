package de.tim0_12432.f1_schedule_app.ui.schedule;

import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Race;

public interface ScheduleListView {
    void showLoading();

    void showRaceEntries(List<Race> entries);
}
