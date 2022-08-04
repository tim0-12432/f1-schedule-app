package de.tim0_12432.f1_schedule_app.data.cache;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.tim0_12432.f1_schedule_app.utility.Logger;

public class CachingService {
    private static final long UPDATE_INTERVAL_HOURS = 48;

    public static void writeData(Context context, String key, Object object) {
        Map<String, Object> cacheObj = new HashMap<>();
        cacheObj.put("time", System.currentTimeMillis());
        cacheObj.put("data", object);
        try {
            StorageService.writeObject(context, key, cacheObj);
        } catch (IOException e) {
            Logger.log(e, "Could not write data to cache", key, "!");
        }
    }

    public static Object readData(Context context, String key) {
        try {
            Object cacheObj = StorageService.readObject(context, key);
            if (cacheObj instanceof Map) {
                Map<String, Object> cacheMap = (Map<String, Object>) cacheObj;
                return cacheMap.get("data");
            }
            throw new ClassCastException("Cache object from is not a map!");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            Logger.log(e, "Could not read data from cache", key, "!");
            return null;
        }
    }

    public static boolean shouldUpdateCache(Context context, String key) {
        try {
            Object cacheObj = StorageService.readObject(context, key);
            if (cacheObj instanceof Map) {
                Map<String, Object> cacheMap = (Map<String, Object>) cacheObj;
                long time = (long) cacheMap.get("time");
                return System.currentTimeMillis() - time > 1000 * 60 * 60 * UPDATE_INTERVAL_HOURS || cacheMap.get("data") == null;
            }
            throw new ClassCastException("Cache object is not a map!");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            Logger.log(e, "Could not read data from cache", key, "!");
            return true;
        }
    }

    public static void clearCache(Context context, String key) {
        StorageService.deleteObject(context, key);
    }
}
