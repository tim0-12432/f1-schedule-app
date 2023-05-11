package de.tim0_12432.f1_schedule_app.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeTest {

    @Test
    public void testGetTimestamp() {
        Time time = Time.valueOf("01:00:00");
        assertEquals("01:00", DateTime.getTimestamp(time));
    }

    @Test
    public void testGetDatestamp() {
        Date date = new Date(0);
        assertEquals("01.01.1970", DateTime.getDatestamp(date));
    }

    @Test
    public void testIsBirthday() {
        Date date1 = Date.valueOf(LocalDateTime.now().minusYears(4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertTrue(DateTime.isBirthday(date1));
        Date date2 = new Date(0);
        assertFalse(DateTime.isBirthday(date2));
    }

    @Test
    public void testGetAge() {
        Date date1 = Date.valueOf(LocalDateTime.now().minusYears(48).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertEquals(48, DateTime.getAge(date1));
        Date date2 = Date.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertEquals(0, DateTime.getAge(date2));
    }

    @Test
    public void testGetDaysDifference() {
        Date date1 = Date.valueOf("2020-01-01");
        Date date2 = Date.valueOf("2020-01-31");
        assertEquals(30, DateTime.getDaysDifference(date1, date2));

        Date date3 = Date.valueOf("2019-12-29");
        assertEquals(-3, DateTime.getDaysDifference(date1, date3));
    }

    @Test
    public void testPlusDays() {
        Date date1 = Date.valueOf("2020-01-01");
        Date date2 = Date.valueOf("2020-01-31");
        assertEquals(date2.getTime(), DateTime.plusDays(date1, 30).getTime());
    }

    @Test
    public void testPlusDaysOverMonth() {
        Date date1 = Date.valueOf("2020-01-29");
        Date date2 = Date.valueOf("2020-02-03");
        assertEquals(date2.getTime(), DateTime.plusDays(date1, 5).getTime());
    }

    @Test
    public void testPlusHours() {
        Time time1 = Time.valueOf("01:00:00");
        Time time2 = Time.valueOf("04:00:00");
        assertEquals(time2.getTime(), DateTime.plusHours(time1, 3).getTime());
    }

    @Test
    public void testPlusHoursOverDay() {
        Time time1 = Time.valueOf("23:00:00");
        Time time2 = Time.valueOf("02:00:00");
        assertEquals(time2.toString(), DateTime.plusHours(time1, 3).toString());
    }

    @Test
    public void testPlusMinutes() {
        Time time1 = Time.valueOf("01:00:00");
        Time time2 = Time.valueOf("01:30:00");
        assertEquals(time2.getTime(), DateTime.plusMinutes(time1, 30).getTime());
    }

    @Test
    public void testPlusMinutesOverHour() {
        Time time1 = Time.valueOf("01:59:00");
        Time time2 = Time.valueOf("02:01:00");
        assertEquals(time2.getTime(), DateTime.plusMinutes(time1, 2).getTime());
    }

    @Test
    public void testMinusMinutes() {
        Time time1 = Time.valueOf("01:30:00");
        Time time2 = Time.valueOf("01:00:00");
        assertEquals(time2.getTime(), DateTime.plusMinutes(time1, -30).getTime());
    }
}
