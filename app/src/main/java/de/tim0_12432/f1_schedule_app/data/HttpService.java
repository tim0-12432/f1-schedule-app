package de.tim0_12432.f1_schedule_app.data;

import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.source.remote.RemoteDataSource;
import de.tim0_12432.f1_schedule_app.data.source.remote.RemoteDataSourceResponseConverter;
import okhttp3.OkHttpClient;

public class HttpService {
    public static final String BASE_URL = "http://ergast.com/api/f1/";

    public static OkHttpClient getClient() {
        return new OkHttpClient();
    }

    public static <T> RemoteDataSource<T> getDataSourceForUrl(String url, RemoteDataSourceResponseConverter<T> converter) {
        return getDataSourceForUrl(url, converter, true);
    }

    public static <T> RemoteDataSource<T> getDataSourceForUrl(String url, RemoteDataSourceResponseConverter<T> converter, boolean useBase) {
        String address = url;
        if (useBase)
            address = BASE_URL + url;

        return new RemoteDataSource<T>(address, converter, getClient());
    }
}
