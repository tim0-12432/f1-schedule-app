package de.tim0_12432.f1_schedule_app.data;

import android.content.Context;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;

import de.tim0_12432.f1_schedule_app.data.cache.CachingService;
import de.tim0_12432.f1_schedule_app.data.entity.AbstractEntity;
import de.tim0_12432.f1_schedule_app.data.parser.AbstractDataSourceParser;
import de.tim0_12432.f1_schedule_app.data.source.ILoadCallback;
import de.tim0_12432.f1_schedule_app.data.source.remote.RemoteDataSource;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class DataManager {

    private final CachingService cachingService;

    public DataManager(Context context) {
        this.cachingService = new CachingService(context);
    }

    public <T extends AbstractEntity> void getDataFrom(Resource resource, ILoadCallback<T> callback) {
        getDataFrom(null, resource, null, callback);
    }

    public <T extends AbstractEntity> void getDataFrom(Date date, Resource resource, String url, ILoadCallback<T> callback) {
        if (cachingService.shouldUpdateCache(resource == Resource.RACE_RESULTS ? date : null, resource, url)) {
            forceGetDataFrom(resource, url, callback);
        } else {
            Object cache = cachingService.readData(resource, url);
            try {
                List<T> data = (List<T>) cache;
                if (data == null) {
                    cachingService.clearCache(resource, url);
                    Logger.log(Logger.LogLevel.WARN, "Cache", CachingService.getKey(resource, url), "was null!");
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

    public <T extends AbstractEntity> void forceGetDataFrom(Resource resource, String url, ILoadCallback<T> callback) {
        AbstractDataSourceParser<T> parser = null;
        try {
            parser = (AbstractDataSourceParser<T>) resource.getParser().newInstance();
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
            Executors.newCachedThreadPool().execute(
                    source.getData(new ILoadCallback<T>() {
                        @Override
                        public void onLoaded(List<T> list) {
                            cachingService.writeData(resource, url, list);
                            callback.onLoaded(list);
                        }
                    })
            );
        } else {
            Logger.log(Logger.LogLevel.ERROR, "Parser", CachingService.getKey(resource, url), "is null!");
        }
    }
}
