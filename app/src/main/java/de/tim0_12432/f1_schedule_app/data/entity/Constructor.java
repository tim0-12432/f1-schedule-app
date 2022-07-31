package de.tim0_12432.f1_schedule_app.data.entity;

public class Constructor {

    private String name;

    private String nationality;

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
