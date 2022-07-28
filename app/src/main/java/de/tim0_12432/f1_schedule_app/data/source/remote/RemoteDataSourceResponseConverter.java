package de.tim0_12432.f1_schedule_app.data.source.remote;

import java.io.InputStream;
import java.util.List;

public interface RemoteDataSourceResponseConverter<T> {
    List<T> convert(InputStream inputStream) throws Exception;
}
