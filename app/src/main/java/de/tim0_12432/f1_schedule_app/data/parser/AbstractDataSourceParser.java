package de.tim0_12432.f1_schedule_app.data.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Entity;
import de.tim0_12432.f1_schedule_app.data.source.remote.RemoteDataSource;
import de.tim0_12432.f1_schedule_app.data.source.remote.RemoteDataSourceResponseConverter;

public abstract class DataSourceParser<T extends Entity> implements RemoteDataSourceResponseConverter<T> {

    public abstract String getUrl();

    @Override
    public List<T> convert(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParser parser = RemoteDataSource.getXmlPullParser();
        return parse(inputStream, parser);
    }

    protected abstract List<T> parse(InputStream input, XmlPullParser parser) throws XmlPullParserException, IOException;
}
