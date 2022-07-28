package de.tim0_12432.f1_schedule_app.data.source;

public interface DataSource<T> {
    void getData(LoadCallback<T> callback);
}
