package de.tim0_12432.f1_schedule_app.data.source;

public interface DataSource<T> {
    Runnable getData(LoadCallback<T> callback);
}
