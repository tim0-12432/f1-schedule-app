package de.tim0_12432.f1_schedule_app.utility;

import android.annotation.SuppressLint;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.sql.Date;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateTime {

    public static String getTimestamp(final Time time) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.ROOT);
        return formatter.format(new Date(time.getTime()));
    }

    public static String getDatestamp(final Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ROOT);
        return formatter.format(new Date(date.getTime()));
    }

    public static String getCurrentYear() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy", Locale.ROOT);
        return formatter.format(new Date(date.getTime()));
    }

    public static Date getToday() {
        return new Date(System.currentTimeMillis());
    }

    @SuppressLint("NewApi")
    public static int getTimezone() {
        TimeZone tzUtc = TimeZone.getTimeZone("UTC");
        LocalDateTime nowUtc = LocalDateTime.now(tzUtc.toZoneId());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime diff = now.minusHours(nowUtc.getHour());
        return diff.getHour();
    }

    @SuppressLint("NewApi")
    public static boolean isBirthday(final Date date) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime birthday = LocalDateTime.ofEpochSecond(date.getTime() / 1000, 0, ZoneOffset.ofHours(getTimezone()));
        return today.getMonth() == birthday.getMonth() && today.getDayOfMonth() == birthday.getDayOfMonth();
    }

    @SuppressLint("NewApi")
    public static int getAge(final Date date) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime birthday = LocalDateTime.ofEpochSecond(date.getTime() / 1000, 0, ZoneOffset.ofHours(getTimezone()));
        return today.getYear() - birthday.getYear();
    }

    @SuppressLint("NewApi")
    public static int getDaysDifference(final Date date1, final Date date2) {
        LocalDateTime day1 = LocalDateTime.ofEpochSecond(date1.getTime() / 1000, 0, ZoneOffset.ofHours(getTimezone())).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime day2 = LocalDateTime.ofEpochSecond(date2.getTime() / 1000, 0, ZoneOffset.ofHours(getTimezone())).withHour(0).withMinute(0).withSecond(0);
        return (int) (day1.until(day2, java.time.temporal.ChronoUnit.DAYS));
    }

    public static Date plusDays(Date date, int days) {
        if (date == null) return new Date(0);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    public static Time plusHours(Time time, int hours) {
        if (time == null) return new Time(0);

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time.getTime());
        c.add(Calendar.HOUR, hours);
        return new Time(c.getTimeInMillis());
    }

    public static Time plusMinutes(Time time, int minutes) {
        if (time == null) return new Time(0);

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time.getTime());
        c.add(Calendar.MINUTE, minutes);
        return new Time(c.getTimeInMillis());
    }
}
