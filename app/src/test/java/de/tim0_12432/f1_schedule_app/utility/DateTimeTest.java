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
        Time time = new Time(0);
        assertEquals("08:00", DateTimeMock.getTimestamp(time, DateTimeMock.getTimezone()));
    }

    @Test
    public void testGetDatestamp() {
        Date date = new Date(0);
        assertEquals("01.01.1970", DateTimeMock.getDatestamp(date));
    }

    @Test
    public void testGetTimezone() {
        assertEquals(7, DateTimeMock.getTimezone());
    }

    @Test
    public void testIsBirthday() {
        Date date1 = Date.valueOf(LocalDateTime.now().minusYears(4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertTrue(DateTimeMock.isBirthday(date1));
        Date date2 = new Date(0);
        assertFalse(DateTimeMock.isBirthday(date2));
    }

    @Test
    public void testGetAge() {
        Date date1 = Date.valueOf(LocalDateTime.now().minusYears(48).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertEquals(48, DateTimeMock.getAge(date1));
        Date date2 = Date.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertEquals(0, DateTimeMock.getAge(date2));
    }
}
