package de.tim0_12432.f1_schedule_app.data.source.remote;

import android.util.Xml;

import androidx.annotation.NonNull;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.inject.Inject;

import de.tim0_12432.f1_schedule_app.data.source.DataSource;
import de.tim0_12432.f1_schedule_app.data.source.LoadCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RemoteDataSource<T> implements DataSource<T> {

    private final String url;
    private final RemoteDataSourceResponseConverter<T> converter;
    private final OkHttpClient client;

    @Inject
    public RemoteDataSource(String url, RemoteDataSourceResponseConverter<T> converter, OkHttpClient client) {
        this.url = url;
        this.converter = converter;
        this.client = client;
    }

    @Override
    public void getData(final LoadCallback<T> callback) {
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/xml")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onLoaded(Collections.emptyList());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (!response.isSuccessful()) {
                    callback.onLoaded(Collections.emptyList());
                }
                try (ResponseBody responseBody = response.body(); InputStream inputStream = responseBody.byteStream()) {
                    callback.onLoaded(converter.convert(inputStream));
                } catch (Exception e) {
                    System.err.println("Error while parsing response: " + e.getMessage());
                    e.printStackTrace();
                    callback.onLoaded(Collections.emptyList());
                }
            }
        });
    }

    public static XmlPullParser getXmlPullParser() throws XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        return parser;
    }
}
