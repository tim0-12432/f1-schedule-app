package de.tim0_12432.f1_schedule_app.data.entity.builder;

import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Location;

public class CircuitBuilder {

    private Location location;

    private String name;

    private String url;

    public CircuitBuilder() {
        this.location = null;
        this.name = null;
        this.url = null;
    }

    public CircuitBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CircuitBuilder withLocation(Location location) {
        this.location = location;
        return this;
    }

    public CircuitBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public Circuit build() {
        return new Circuit(location, name, url);
    }
}
