package de.tim0_12432.f1_schedule_app.data.entity;

import android.annotation.SuppressLint;

import java.time.LocalDate;

public class Driver {

    private String code;

    private String name;

    private String familyName;

    private LocalDate dateOfBirth;

    private int number;

    private String nationality;

    private String url;

    @SuppressLint("NewApi")
    public Driver(String code, String name, String familyName, String dateOfBirth, int number, String nationality, String url) {
        new Driver(code, name, familyName, LocalDate.parse(dateOfBirth), number, nationality, url);
    }

    public Driver(String code, String name, String familyName, LocalDate dateOfBirth, int number, String nationality, String url) {
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getNumber() {
        return number;
    }

    public String getNationality() {
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
}
