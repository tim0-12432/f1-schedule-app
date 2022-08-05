package de.tim0_12432.f1_schedule_app;

import android.util.Log;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

import java.util.Arrays;

@Implements(Log.class)
public class TestLog {

    @Implementation
    public static int v(String tag, String msg) {
        System.out.println("DEBUG: " + tag + " " + msg);
        return 0;
    }

    @Implementation
    public static int d(String tag, String msg) {
        System.out.println("DEBUG: " + tag + " " + msg);
        return 0;
    }

    @Implementation
    public static int i(String tag, String msg) {
        System.out.println("INFO: " + tag + " " + msg);
        return 0;
    }

    @Implementation
    public static int w(String tag, String msg) {
        System.out.println("WARN: " + tag + " " + msg);
        return 0;
    }

    @Implementation
    public static int e(String tag, String msg) {
        System.err.println("ERROR: " + tag + " " + msg);
        return 0;
    }

    @Implementation
    public static int e(String tag, String msg, Throwable tr) {
        System.err.println("ERROR: " + tag + " " + msg + " " + Arrays.toString(tr.getStackTrace()));
        return 0;
    }
}
