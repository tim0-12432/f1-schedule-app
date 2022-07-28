package de.tim0_12432.f1_schedule_app.data.entity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="Location")
public class Location {

    @Attribute(name="lat")
    private final double latitude;

    @Attribute(name="long")
    private final double longitude;

    @Element(name="Locality")
    private final String locality;

    @Element(name="Country")
    private final String country;

    public Location(@Attribute(name="lat") double latitude, @Attribute(name="long") double longitude,
                    @Element(name="Locality") String locality, @Element(name="Country") String country) {
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
}
