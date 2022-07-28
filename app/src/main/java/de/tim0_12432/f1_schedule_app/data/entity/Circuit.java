package de.tim0_12432.f1_schedule_app.data.entity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="Circuit")
public class Circuit {

    @Element(name="Location")
    private final Location location;

    @Element(name="CircuitName")
    private final String name;

    @Attribute(name="url")
    private final String url;

    public Circuit(@Element(name="Location") Location location, @Element(name="CircuitName") String name, @Attribute(name="url") String url) {
        this.location = location;
        this.name = name;
        this.url = url;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public static Circuit copyOf(Circuit c) {
        return new Circuit(c.getLocation(), c.getName(), c.getUrl());
    }
}
