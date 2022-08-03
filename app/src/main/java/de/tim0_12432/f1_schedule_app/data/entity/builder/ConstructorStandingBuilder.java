package de.tim0_12432.f1_schedule_app.data.entity.builder;

import de.tim0_12432.f1_schedule_app.data.entity.Constructor;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorStanding;

public class ConstructorStandingBuilder extends EntityBuilder<ConstructorStanding> {

    private int position;

    private int points;

    private int wins;

    private Constructor constructor;

    public ConstructorStandingBuilder() {
        this.position = 0;
        this.points = 0;
        this.wins = 0;
        this.constructor = null;
    }

    public ConstructorStandingBuilder withPosition(int position) {
        this.position = position;
        return this;
    }

    public ConstructorStandingBuilder withPoints(int points) {
        this.points = points;
        return this;
    }

    public ConstructorStandingBuilder withWins(int wins) {
        this.wins = wins;
        return this;
    }

    public ConstructorStandingBuilder withConstructor(Constructor constructor) {
        this.constructor = constructor;
        return this;
    }

    public ConstructorStanding build() {
        return new ConstructorStanding(position, points, wins, constructor);
    }
}
