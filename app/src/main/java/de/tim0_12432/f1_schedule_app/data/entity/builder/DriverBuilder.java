package de.tim0_12432.f1_schedule_app.data.entity.builder;

import java.sql.Date;

import de.tim0_12432.f1_schedule_app.data.entity.Driver;

public class DriverBuilder {

    private String code;

    private String name;

    private String familyName;

    private Date dateOfBirth;

    private int number;

    private String nationality;

    private String url;

    public DriverBuilder() {
        this.code = null;
        this.name = null;
        this.familyName = null;
        this.dateOfBirth = null;
        this.number = -1;
        this.nationality =
        this.url = null;
    }

    public DriverBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public DriverBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public DriverBuilder withFamilyName(String familyName) {
        this.familyName = familyName;
        return this;
    }

    public DriverBuilder withDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public DriverBuilder withNumber(int number) {
        this.number = number;
        return this;
    }

    public DriverBuilder withNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public DriverBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public Driver build() {
        return new Driver(code, name, familyName, dateOfBirth, number, nationality, url);
    }
}
