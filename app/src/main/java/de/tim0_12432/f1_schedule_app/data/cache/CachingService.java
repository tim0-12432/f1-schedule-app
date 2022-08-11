package de.tim0_12432.f1_schedule_app.data.cache;

import android.content.Context;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.tim0_12432.f1_schedule_app.data.Resource;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class CachingService {
    protected StorageService storageService;

    public CachingService(Context context) {
        this.storageService = new StorageService(context);
    }

    public void writeData(Resource resource, String url, Object object) {
        Map<String, Object> cacheObj = new HashMap<>();
        cacheObj.put("time", System.currentTimeMillis());
        cacheObj.put("data", object);
        try {
            storageService.writeObject(getKey(resource, url), cacheObj);
        } catch (IOException e) {
            Logger.log(e, "Could not write data to cache", getKey(resource, url), "!");
        }
    }

    public Object readData(Resource resource, String url) {
        try {
            Object cacheObj = storageService.readObject(getKey(resource, url));
            if (cacheObj instanceof Map) {
                Map<String, Object> cacheMap = (Map<String, Object>) cacheObj;
                return cacheMap.get("data");
            }
            throw new ClassCastException("Cache object from is not a map!");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            Logger.log(e, "Could not read data from cache", getKey(resource, url), "!");
            return null;
        }
    }

    public boolean shouldUpdateCache(Date date, Resource resource, String url) {
        try {
            Object cacheObj = storageService.readObject(getKey(resource, url));
            if (cacheObj instanceof Map) {
                Map<String, Object> cacheMap = (Map<String, Object>) cacheObj;
                long time = (long) cacheMap.get("time");
                if (date != null) {
                    long dateTime = date.getTime();
                    if (time <= dateTime && dateTime <= new Date(System.currentTimeMillis()).getTime()) {
                        // cache is older than date but date is older then today
                        return true;
                    }
                }
                return System.currentTimeMillis() - time > 1000L * 60 * 60 * resource.getUpdateIntervalHours() || cacheMap.get("data") == null;
            }
            throw new ClassCastException("Cache object is not a map!");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            Logger.log(Logger.LogLevel.WARN, "Could not read data from cache", getKey(resource, url) + "!", e.getMessage());
            return true;
        }
    }

    public void clearCache(Resource resource, String url) {
        storageService.deleteObject(getKey(resource, url));
    }

    public static String getKey(Resource resource, String url) {
        if (url == null) {
            return resource.name().toLowerCase(Locale.ROOT);
        } else {
            return resource.name().toLowerCase(Locale.ROOT) + "-" + url.toLowerCase(Locale.ROOT).replaceAll("[/.:]", "");
        }
    }
}
