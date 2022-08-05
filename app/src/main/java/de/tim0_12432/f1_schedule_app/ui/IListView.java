package de.tim0_12432.f1_schedule_app.ui;

import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Race;

public interface IListView<T> {
    void showLoading();

    void showEntries(List<T> entries);
}
