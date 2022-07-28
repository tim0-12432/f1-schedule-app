package de.tim0_12432.f1_schedule_app.data.parser;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.source.remote.RemoteDataSourceResponseConverter;

public abstract class DataSourceParser<T> implements RemoteDataSourceResponseConverter<T> {

    @Override
    public List<T> convert(InputStream inputStream) throws Exception {
        Serializer serializer = new Persister();
        return parse(inputStream, serializer);
    }

    protected abstract List<T> parse(InputStream input, Serializer serializer) throws Exception;
}
