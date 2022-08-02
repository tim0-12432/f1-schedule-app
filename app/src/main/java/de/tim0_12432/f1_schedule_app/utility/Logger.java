package de.tim0_12432.f1_schedule_app.utility;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.Arrays;

public class Logger {
    public enum LogLevel {
        VERBOSE(2),
        DEBUG(3),
        INFO(4),
        WARN(5),
        ERROR(6),
        ASSERT(7);

        private final int code;

        LogLevel(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
    }

    public static void log(Object... message) {
        log(LogLevel.INFO, message);
    }

    @SuppressLint("NewApi")
    public static void log(LogLevel level, Object... message) {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
        String tag = caller.getClassName() + "#" + caller.getMethodName();
        String msg = String.join(" ", Arrays.stream(message).map(Object::toString).toArray(String[]::new));
        switch (level) {
            case VERBOSE:
                Log.v(tag, msg);
                break;
            case DEBUG:
                Log.d(tag, msg);
                break;
            case WARN:
                Log.w(tag, msg);
                break;
            case ERROR:
                Log.e(tag, msg);
                break;
            case ASSERT:
                Log.wtf(tag, msg);
                break;
            case INFO:
            default:
                Log.i(tag, msg);
                break;
        }
    }

    @SuppressLint("NewApi")
    public static void log(Throwable throwable, Object... message) {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
        String msg = String.join(" ", Arrays.stream(message).map(Object::toString).toArray(String[]::new));
        Log.e(caller.getClassName() + "#" + caller.getMethodName(), msg, throwable);
    }
}
