package de.tim0_12432.f1_schedule_app.data.entity.builder;

import de.tim0_12432.f1_schedule_app.data.entity.Constructor;

public class ConstructorBuilder {
    private String name;

    private String nationality;

    private String url;

    public ConstructorBuilder() {
        this.name = null;
        this.nationality = null;
        this.url = null;
    }

    public ConstructorBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ConstructorBuilder withNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public ConstructorBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public Constructor build() {
        return new Constructor(name, nationality, url);
    }
}
