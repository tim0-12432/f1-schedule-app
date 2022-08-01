package de.tim0_12432.f1_schedule_app.data;

import android.content.Context;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import de.tim0_12432.f1_schedule_app.data.cache.CachingService;
import de.tim0_12432.f1_schedule_app.data.parser.DataSourceParser;
import de.tim0_12432.f1_schedule_app.data.source.LoadCallback;
import de.tim0_12432.f1_schedule_app.data.source.remote.RemoteDataSource;
import de.tim0_12432.f1_schedule_app.utility.Logging;

public class DataManager {

    private final Context context;

    public DataManager(Context context) {
        this.context = context;
    }

    public <T> void getDataFrom(ResourceNames resource, LoadCallback<T> callback) {
        getDataFrom(resource, resource.getUrl(), callback);
    }

    public <T> void getDataFrom(ResourceNames resource, String url, LoadCallback<T> callback) {
        if (CachingService.shouldUpdateCache(context, getKey(resource, url))) {
            DataSourceParser<T> parser = null;
            try {
                parser = (DataSourceParser<T>) resource.getParser().newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                Logging.Log(e, "Could not initialize parser class!");
                e.printStackTrace();
            }
            if (parser != null) {
                RemoteDataSource<T> source;
                if (url == null) {
                    source = HttpService.getDataSourceForUrl(parser);
                } else {
                    source = HttpService.getDataSourceForUrl(url, parser);
                }
                source.getData(new LoadCallback<T>() {
                    @Override
                    public void onLoaded(List<T> list) {
                        CachingService.writeData(context, getKey(resource, url), list);
                        callback.onLoaded(list);
                    }
                });
            } else {
                Logging.Log(Logging.LogLevel.ERROR, "Parser", getKey(resource, url), "is null!");
            }
        } else {
            Object cache = CachingService.readData(context, getKey(resource, url));
            try {
                List<T> data = (List<T>) cache;
                if (data == null) {
                    CachingService.clearCache(context, getKey(resource, url));
                    Logging.Log(Logging.LogLevel.WARN, "Cache", getKey(resource, url), "was null!");
                    callback.onLoaded(Collections.emptyList());
                } else {
                    callback.onLoaded(data);
                }
            } catch (ClassCastException e) {
                Logging.Log(e, "Could not cast cache object to list!");
                callback.onLoaded(Collections.emptyList());
            }
        }
    }

    private static String getKey(ResourceNames resource, String url) {
        if (url == null) {
            return resource.name().toLowerCase(Locale.ROOT);
        } else {
            return resource.name().toLowerCase(Locale.ROOT) + "-" + url.toLowerCase(Locale.ROOT).replaceAll("[/.:]", "");
        }
    }
}
