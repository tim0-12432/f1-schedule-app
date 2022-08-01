package de.tim0_12432.f1_schedule_app.data;

import android.annotation.SuppressLint;
import android.util.Log;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class Utility {
    public static String getTimestamp(final Time time) {
        Time newTime = new Time(time.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        newTime.setTime(newTime.getTime() + ((long) getTimezone() * 60 * 60 * 1000));
        return formatter.format(new Date(newTime.getTime()));
    }

    public static String getDatestamp(final Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(new Date(date.getTime()));
    }

    @SuppressLint("NewApi")
    public static int getTimezone() {
        TimeZone tzUtc = TimeZone.getTimeZone("UTC");
        LocalDateTime nowUtc = LocalDateTime.now(tzUtc.toZoneId());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime diff = now.minusHours(nowUtc.getHour());
        return diff.getHour();
    }

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

    public static void Log(Object... message) {
        Log(LogLevel.INFO, message);
    }

    @SuppressLint("NewApi")
    public static void Log(LogLevel level, Object... message) {
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
    public static void Log(Throwable throwable, Object... message) {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
        String msg = String.join(" ", Arrays.stream(message).map(Object::toString).toArray(String[]::new));
        Log.e(caller.getClassName() + "#" + caller.getMethodName(), msg, throwable);
    }
}
