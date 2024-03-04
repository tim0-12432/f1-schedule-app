package de.tim0_12432.f1_schedule_app.utility;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static List<ErrorLogEntry> errors = new ArrayList<>();

    public static void log(Object... message) {
        log(LogLevel.INFO, message);
    }

    @SuppressLint("NewApi")
    public static void log(LogLevel level, Object... message) {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
        String tag = caller.getClassName() + "#" + caller.getMethodName();
        String msg = String.join(" ", Arrays.stream(message).map(m -> m == null ? "null" : m.toString()).toArray(String[]::new));
        switch (level) {
            case VERBOSE:
                Log.v(tag, msg);
                break;
            case DEBUG:
                Log.d(tag, msg);
                break;
            case WARN:
                errors.add(new ErrorLogEntry(LogLevel.WARN, tag, msg));
                Log.w(tag, msg);
                break;
            case ERROR:
                errors.add(new ErrorLogEntry(LogLevel.ERROR, tag, msg));
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
        String msg = String.join(" ", Arrays.stream(message).map(m -> m == null ? "null" : m.toString()).toArray(String[]::new));
        String tag = caller.getClassName() + "#" + caller.getMethodName();
        errors.add(new ErrorLogEntry(LogLevel.ERROR, tag, msg));
        Log.e(tag, msg, throwable);
    }

    public static class ErrorLogEntry {
        private final LogLevel level;
        private final String tag;
        private final String msg;

        public ErrorLogEntry(LogLevel level, String tag, String msg) {
            this.level = level;
            this.tag = tag;
            this.msg = msg;
        }

        public LogLevel getLevel() {
            return level;
        }

        public String getTag() {
            return tag;
        }

        public String getMsg() {
            return msg;
        }
    }
}
