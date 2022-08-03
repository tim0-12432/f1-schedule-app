package de.tim0_12432.f1_schedule_app.data.entity.builder;

import org.junit.Assert;
import org.junit.Test;

import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Location;

public class CircuitBuilderTest {
    @Test
    public void testDefault() {
        CircuitBuilder builder = new CircuitBuilder();
        Circuit circ = builder.build();
        Assert.assertNull(circ.getName());
        Assert.assertNull(circ.getLocation());
        Assert.assertNull(circ.getUrl());
    }

    @Test
    public void testAttributes() {
        String url = "http://www.google.de";
        String name = "Test";
        Location loc = new Location(1, 2, "TestLoc", "TestCountry");
        CircuitBuilder builder = new CircuitBuilder()
                .withUrl(url)
                .withName(name)
                .withLocation(loc);
        Circuit circ = builder.build();
        Assert.assertEquals(url, circ.getUrl());
        Assert.assertEquals(name, circ.getName());
        Assert.assertEquals(loc, circ.getLocation());
    }
}
