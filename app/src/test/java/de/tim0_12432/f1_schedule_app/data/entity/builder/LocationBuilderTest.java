package de.tim0_12432.f1_schedule_app.data.entity.builder;

import org.junit.Assert;
import org.junit.Test;

import de.tim0_12432.f1_schedule_app.data.entity.Location;

public class LocationBuilderTest {
    @Test
    public void testDefault() {
        LocationBuilder builder = new LocationBuilder();
        Location loc = builder.build();
        Assert.assertNull(loc.getCountry());
        Assert.assertNull(loc.getLocality());
        Assert.assertEquals(-1, loc.getLatitude(), 0.0);
        Assert.assertEquals(-1, loc.getLongitude(), 0.0);
    }

    @Test
    public void testAttributes() {
        double lat = 1.0;
        double lon = 2.0;
        String locale = "TestLoc";
        String country = "TestCountry";
        LocationBuilder builder = new LocationBuilder()
                .withLat(lat)
                .withLong(lon)
                .withLocality(locale)
                .withCountry(country);
        Location loc = builder.build();
        Assert.assertEquals(lat, loc.getLatitude(), 0.0);
        Assert.assertEquals(lon, loc.getLongitude(), 0.0);
        Assert.assertEquals(locale, loc.getLocality());
        Assert.assertEquals(country, loc.getCountry());
    }
}
