package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.NonNull;

public class Constructor {

    private final String name;

    private final Nationality nationality;

    private final String url;

    public Constructor(String name, String nationality, String url) {
        this(name, Nationality.getNationalityOfTranslation(nationality), url);
    }

    public Constructor(String name, Nationality nationality, String url) {
        this.name = name;
        this.nationality = nationality;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public String getUrl() {
        return url;
    }

    public static Constructor copyOf(Constructor c) {
        return new Constructor(c.getName(), c.getNationality(), c.getUrl());
    }

    @NonNull
    @Override
    public String toString() {
        return "Constructor{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
