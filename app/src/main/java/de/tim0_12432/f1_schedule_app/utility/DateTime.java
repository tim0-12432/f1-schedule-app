package de.tim0_12432.f1_schedule_app.utility;

import android.annotation.SuppressLint;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

public class DateTime {
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
}
