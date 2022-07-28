package de.tim0_12432.f1_schedule_app.data.entity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="Constructor")
public class Constructor {

    @Element(name="Name")
    private String name;

    @Element(name="Nationality")
    private String nationality;

    @Attribute(name="url")
    private String url;

    public Constructor(String name, String nationality, String url) {
        this.name = name;
        this.nationality = nationality;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getUrl() {
        return url;
    }

    public static Constructor copyOf(Constructor c) {
        return new Constructor(c.getName(), c.getNationality(), c.getUrl());
    }
}
