package de.tim0_12432.f1_schedule_app.data.entity;

import android.util.TypedValue;

import java.util.Locale;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;

public enum ConstructorAttr {
    FERR("Scuderia Ferrari", R.color.red_english_vermillion, Nationality.ITALIAN, EngineConstructor.Ferrari),
    MERC("Mercedes-AMG Petronas Motorsport", R.color.green_keppel, Nationality.GERMAN, EngineConstructor.Mercedes),
    HAAS("Haas F1 Team", R.color.blue_yonder, Nationality.AMERICAN, EngineConstructor.Ferrari),
    ALFA("Alfa Romeo F1 Team ORLEN", R.color.red_rose_vale, Nationality.SWISS, EngineConstructor.Ferrari),
    ALPH("Scuderia AlphaTauri", R.color.black_charcoal, Nationality.ITALIAN, EngineConstructor.RedBull),
    ALPI("Alpine F1 Team", R.color.purple_middle, Nationality.FRENCH, EngineConstructor.Renault),
    ASTO("Aston Martin", R.color.green_viridian, Nationality.BRITISH, EngineConstructor.Mercedes),
    WILL("Williams Racing", R.color.blue_lapis_lazuli, Nationality.BRITISH, EngineConstructor.Mercedes),
    MCLA("McLaren Racing", R.color.orange_persian, Nationality.BRITISH, EngineConstructor.Mercedes),
    REDB("Red Bull Racing", R.color.blue_prussian, Nationality.AUSTRIAN, EngineConstructor.RedBull),
    DEFAULT();

    private final String name;
    private final int colorId;
    private final Nationality nationality;
    private final EngineConstructor engine;

    ConstructorAttr() {
        TypedValue color = new TypedValue();
        MainActivity.getAppContext().getTheme().resolveAttribute(com.google.android.material.R.attr.colorOnSecondary, color, true);
        this.name = "NaN";
        this.colorId = color.resourceId;
        this.nationality = Nationality.DEFAULT;
        this.engine = null;
    }

    ConstructorAttr(String name, int colorId, Nationality nationality, EngineConstructor engine) {
        this.name = name;
        this.colorId = colorId;
        this.nationality = nationality;
        this.engine = engine;
    }

    public int getColor() {
        return MainActivity.getAppResources().getColor(colorId);
    }

    public int getColorId() {
        return colorId;
    }

    public String getName() {
        return name;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public EngineConstructor getEngine() {
        return engine;
    }

    public static ConstructorAttr getConstructorOfName(String name) {
        try {
            return ConstructorAttr.valueOf(name.replace(" ", "").toUpperCase(Locale.ROOT).substring(0, 4));
        } catch (IllegalArgumentException e) {
            return ConstructorAttr.DEFAULT;
        }
    }

    public static ConstructorAttr getConstructorOfTeam(Constructor constructor) {
        if (constructor == null) {
            return ConstructorAttr.DEFAULT;
        }
        return ConstructorAttr.getConstructorOfName(constructor.getName());
    }
}