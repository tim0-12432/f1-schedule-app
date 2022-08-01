package de.tim0_12432.f1_schedule_app.data.cache;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StorageService {
    public static void writeObject(Context context, String key, Object object) throws IOException {
        FileOutputStream fileStream = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
        objectStream.writeObject(object);
        objectStream.close();
        fileStream.close();
    }

    public static Object readObject(Context context, String key) throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileStream = context.openFileInput(key);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            Object object = objectStream.readObject();
            return object;
        } catch (FileNotFoundException e) {
            throw new IOException("Cache file not found!");
        }
    }

    public static void deleteObject(Context context, String key) {
        context.deleteFile(key);
    }
}
