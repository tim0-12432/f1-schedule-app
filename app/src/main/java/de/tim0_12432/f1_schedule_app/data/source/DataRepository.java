package de.tim0_12432.f1_schedule_app.data.source;

import java.util.List;

import javax.inject.Inject;

import de.tim0_12432.f1_schedule_app.data.source.remote.RemoteDataSource;

public class DataRepository<T> implements DataSource<T> {
    private RemoteDataSource remoteDataSource;

    @Inject
    public DataRepository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getData(final LoadCallback<T> callback) {
        remoteDataSource.getData(new LoadCallback() {
            @Override
            public void onLoaded(List entries) {
                callback.onLoaded(entries);
            }
        });
    }
}
