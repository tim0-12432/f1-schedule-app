package de.tim0_12432.f1_schedule_app.data;

import java.util.Arrays;

import de.tim0_12432.f1_schedule_app.data.entity.AbstractEntity;
import de.tim0_12432.f1_schedule_app.data.parser.AbstractDataSourceParser;
import de.tim0_12432.f1_schedule_app.data.source.remote.RemoteDataSource;
import de.tim0_12432.f1_schedule_app.data.source.remote.IRemoteDataSourceResponseConverter;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;

public class HttpService {
    public static final String BASE_URL = "https://ergast.com/api/f1/";

    public static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS))
                .build();
    }

    public static <T extends AbstractEntity> RemoteDataSource<T> getDataSourceForUrl(AbstractDataSourceParser<T> converter) {
        return getDataSourceForUrl(converter.getUrl(), converter, true);
    }

    public static <T extends AbstractEntity> RemoteDataSource<T> getDataSourceForUrl(String url, IRemoteDataSourceResponseConverter<T> converter) {
        return getDataSourceForUrl(url, converter, true);
    }

    public static <T extends AbstractEntity> RemoteDataSource<T> getDataSourceForUrl(String url, IRemoteDataSourceResponseConverter<T> converter, boolean useBase) {
        String address = url;
        if (useBase)
            address = BASE_URL + url;

        return new RemoteDataSource<T>(address, converter, getClient());
    }
}
