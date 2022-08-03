package de.tim0_12432.f1_schedule_app.utility;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.sql.Date;
import java.sql.Time;

public class DateTimeTest {
    @Test
    public void testGetTimestamp() {
        Time time = new Time(0);
        assertEquals("03:00", DateTime.getTimestamp(time));
    }

    @Test
    public void testGetDatestamp() {
        Date date = new Date(0);
        assertEquals("01.01.1970", DateTime.getDatestamp(date));
    }

    @Test
    public void testGetTimezone() {
        assertEquals(2, DateTime.getTimezone());
    }
}
