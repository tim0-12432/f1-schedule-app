package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.sql.Date;

public class Driver implements Serializable {

    private final String code;

    private final String name;

    private final String familyName;

    private final Date dateOfBirth;

    private final int number;

    private final Nationality nationality;

    private final String url;

    public Driver(String code, String name, String familyName, String dateOfBirth, int number, String nationality, String url) {
        this(code, name, familyName, Date.valueOf(dateOfBirth), number, nationality, url);
    }

    public Driver(String code, String name, String familyName, Date dateOfBirth, int number, String nationality, String url) {
        this(code, name, familyName, dateOfBirth, number, Nationality.getNationalityOfTranslation(nationality), url);
    }

    public Driver(String code, String name, String familyName, Date dateOfBirth, int number, Nationality nationality, String url) {
        this.code = code;
        this.name = name;
        this.familyName = familyName;
        this.dateOfBirth = dateOfBirth;
        this.number = number;
        this.nationality = nationality;
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getNumber() {
        return number;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public String getUrl() {
        return url;
    }

    public static Driver copyOf(Driver d) {
        return new Driver(
                d.getCode(),
                d.getName(),
                d.getFamilyName(),
                d.getDateOfBirth(),
                d.getNumber(),
                d.getNationality(),
                d.getUrl());
    }

    @NonNull
    @Override
    public String toString() {
        return "Driver{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", number=" + number +
                ", nationality='" + nationality + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
