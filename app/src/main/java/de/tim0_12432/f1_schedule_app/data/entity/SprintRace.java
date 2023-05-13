package de.tim0_12432.f1_schedule_app.data.entity;

import java.util.Locale;

public enum SprintRace {
    _2022(4, 11, 22),
    _2023(4, 10, 13, 18, 19, 21),
    DEFAULT();

    private final int[] numbers;

    SprintRace(int... numbers) {
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public static SprintRace getSprintsByYear(int year) {
        try {
            return SprintRace.valueOf("_" + year);
        } catch (IllegalArgumentException e) {
            return SprintRace.DEFAULT;
        }
    }

    public static SprintRace getSprintsByYear(String year) {
        try {
            return SprintRace.valueOf("_" + year);
        } catch (IllegalArgumentException e) {
            return SprintRace.DEFAULT;
        }
    }
}
