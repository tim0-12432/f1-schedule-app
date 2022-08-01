package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Location implements Serializable {

    private final double latitude;

    private final double longitude;

    private final String locality;

    private final String country;

    public Location(double latitude, double longitude, String locality, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.locality = locality;
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLocality() {
        return locality;
    }

    public String getCountry() {
        return country;
    }

    @NonNull
    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", locality='" + locality + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
