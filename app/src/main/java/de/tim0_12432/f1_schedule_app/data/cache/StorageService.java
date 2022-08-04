package de.tim0_12432.f1_schedule_app.data.cache;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StorageService {

    private final Context context;

    public StorageService(Context context) {
        this.context = context;
    }

    public void writeObject(String key, Object object) throws IOException {
        FileOutputStream fileStream = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
        objectStream.writeObject(object);
        objectStream.close();
        fileStream.close();
    }

    public Object readObject(String key) throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileStream = context.openFileInput(key);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            return objectStream.readObject();
        } catch (FileNotFoundException e) {
            throw new IOException("Cache file not found!");
        }
    }

    public void deleteObject(String key) {
        context.deleteFile(key);
    }
}
