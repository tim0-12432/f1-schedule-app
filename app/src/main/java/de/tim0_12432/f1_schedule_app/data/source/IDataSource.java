package de.tim0_12432.f1_schedule_app.data.source;

public interface IDataSource<T> {
    Runnable getData(ILoadCallback<T> callback);
}
