package de.tim0_12432.f1_schedule_app.utility;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SpeedTest {
    @Test
    public void testConvertMphToKmh() {
        assertEquals("0.0", Speed.convertMphToKmh(""));
        assertEquals("0.0", Speed.convertMphToKmh(null));
        assertEquals("0.0", Speed.convertMphToKmh("0.0"));
        assertEquals("1.61", Speed.convertMphToKmh("1.0"));
        assertEquals("16.1", Speed.convertMphToKmh("10.0"));
        assertEquals("80.5", Speed.convertMphToKmh("50.0"));
        assertEquals("161.0", Speed.convertMphToKmh("100.0"));
        assertEquals("322.0", Speed.convertMphToKmh("200.0"));
    }

    @Test
    public void testConvertKmhToMph() {
        assertEquals("0.0", Speed.convertKmhToMph(""));
        assertEquals("0.0", Speed.convertKmhToMph(null));
        assertEquals("0.0", Speed.convertKmhToMph("0.0"));
        assertEquals("0.62", Speed.convertKmhToMph("1.0"));
        assertEquals("6.2", Speed.convertKmhToMph("10.0"));
        assertEquals("31.0", Speed.convertKmhToMph("50.0"));
        assertEquals("62.0", Speed.convertKmhToMph("100.0"));
        assertEquals("124.0", Speed.convertKmhToMph("200.0"));
    }
}
