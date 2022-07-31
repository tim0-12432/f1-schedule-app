package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.NonNull;

public class Circuit {

    private final Location location;

    private final String name;

    private final String url;

    public Circuit(Location location, String name, String url) {
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

    @NonNull
    @Override
    public String toString() {
        return "Circuit{" +
                "location=" + location +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
