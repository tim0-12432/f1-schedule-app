package de.tim0_12432.f1_schedule_app.data;

import android.content.Context;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import de.tim0_12432.f1_schedule_app.data.cache.CachingService;
import de.tim0_12432.f1_schedule_app.data.entity.Entity;
import de.tim0_12432.f1_schedule_app.data.parser.DataSourceParser;
import de.tim0_12432.f1_schedule_app.data.source.LoadCallback;
import de.tim0_12432.f1_schedule_app.data.source.remote.RemoteDataSource;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class DataManager {

    private final CachingService cachingService;

    public DataManager(Context context) {
        this.cachingService = new CachingService(context);
    }

    public <T extends Entity> void getDataFrom(ResourceNames resource, LoadCallback<T> callback) {
        getDataFrom(resource, null, callback);
    }

    public <T extends Entity> void getDataFrom(ResourceNames resource, String url, LoadCallback<T> callback) {
        if (cachingService.shouldUpdateCache(getKey(resource, url))) {
            DataSourceParser<T> parser = null;
            try {
                parser = (DataSourceParser<T>) resource.getParser().newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                Logger.log(e, "Could not initialize parser class!");
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
                        cachingService.writeData(getKey(resource, url), list);
                        callback.onLoaded(list);
                    }
                });
            } else {
                Logger.log(Logger.LogLevel.ERROR, "Parser", getKey(resource, url), "is null!");
            }
        } else {
            Object cache = cachingService.readData(getKey(resource, url));
            try {
                List<T> data = (List<T>) cache;
                if (data == null) {
                    cachingService.clearCache(getKey(resource, url));
                    Logger.log(Logger.LogLevel.WARN, "Cache", getKey(resource, url), "was null!");
                    callback.onLoaded(Collections.emptyList());
                } else {
                    callback.onLoaded(data);
                }
            } catch (ClassCastException e) {
                Logger.log(e, "Could not cast cache object to list!");
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
