package de.tim0_12432.f1_schedule_app.data.source.remote;

import androidx.annotation.NonNull;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.inject.Inject;

import de.tim0_12432.f1_schedule_app.data.source.IDataSource;
import de.tim0_12432.f1_schedule_app.data.source.ILoadCallback;
import de.tim0_12432.f1_schedule_app.utility.Logger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RemoteDataSource<T> implements IDataSource<T> {

    private final String url;
    private final IRemoteDataSourceResponseConverter<T> converter;
    private final OkHttpClient client;

    @Inject
    public RemoteDataSource(String url, IRemoteDataSourceResponseConverter<T> converter, OkHttpClient client) {
        this.url = url;
        this.converter = converter;
        this.client = client;
    }

    @Override
    public Runnable getData(final ILoadCallback<T> callback) {
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/xml")
                .build();

        Logger.log("\u2192", "GET", url);
        return new Runnable() {
            @Override
            public void run() {
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Logger.log("\u2190", "GET", url, "FAILED");
                        callback.onLoaded(Collections.emptyList());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                        Logger.log("\u2190", "GET", url, response.code(), "(" + (response.receivedResponseAtMillis() - response.sentRequestAtMillis()) + "ms)");
                        if (!response.isSuccessful()) {
                            callback.onLoaded(Collections.emptyList());
                        }
                        try (ResponseBody responseBody = response.body(); InputStream inputStream = responseBody.byteStream()) {
                            callback.onLoaded(converter.convert(inputStream));
                        } catch (Exception e) {
                            Logger.log(e, "Error while parsing response:", e.getMessage());
                            callback.onLoaded(Collections.emptyList());
                        }
                    }
                });
            }
        };
    }

    public static XmlPullParser getXmlPullParser() throws XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        return parser;
    }
}
