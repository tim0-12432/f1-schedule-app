package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.NonNull;

public class NonDriverPerson extends AbstractEntity{

    private final String name;

    private final String familyName;

    private final Nationality nationality;

    private final String url;

    public NonDriverPerson(String name, String familyName, Nationality nationality) {
        this.name = name;
        this.familyName = familyName;
        this.nationality = nationality;
        this.url = null;
    }

    public NonDriverPerson(String name, String familyName, Nationality nationality, String url) {
        this.name = name;
        this.familyName = familyName;
        this.nationality = nationality;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public String getUrl() {
        return url;
    }

    @NonNull
    @Override
    public String toString() {
        return "NonDriverPerson{" +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
