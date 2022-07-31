package de.tim0_12432.f1_schedule_app.data.entity.builder;

import de.tim0_12432.f1_schedule_app.data.entity.Location;

public class LocationBuilder {

    private double latitude;

    private double longitude;

    private String locality;

    private String country;

    public LocationBuilder() {
        this.latitude = -1;
        this.longitude = -1;
        this.locality = null;
        this.country = null;
    }

    public LocationBuilder withLat(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public LocationBuilder withLong(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public LocationBuilder withLocality(String locality) {
        this.locality = locality;
        return this;
    }

    public LocationBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public Location build() {
        return new Location(latitude, longitude, locality, country);
    }
}
