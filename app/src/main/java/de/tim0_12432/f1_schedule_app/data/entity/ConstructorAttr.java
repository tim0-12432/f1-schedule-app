package de.tim0_12432.f1_schedule_app.data.entity;

import android.util.TypedValue;

import java.util.Locale;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;

public enum ConstructorAttr {
    FERR("Scuderia Ferrari", R.color.red_english_vermillion, Nationality.ITALIAN, EngineConstructor.Ferrari, new NonDriverPerson("Frédéric", "Vasseur", Nationality.FRENCH)), // Ferrari
    MERC("Mercedes-AMG Petronas Motorsport", R.color.green_keppel, Nationality.GERMAN, EngineConstructor.Mercedes, new NonDriverPerson("Toto", "Wolff", Nationality.AUSTRIAN), new NonDriverPerson("James", "Allison", Nationality.BRITISH)), // Mercedes
    HAAS("MoneyGram Haas F1 Team", R.color.blue_yonder, Nationality.AMERICAN, EngineConstructor.Ferrari, new NonDriverPerson("Günther", "Steiner", Nationality.ITALIAN), new NonDriverPerson("Rob", "Taylor", Nationality.BRITISH)), // Haas
    ALFA("Alfa Romeo F1 Team Stake", R.color.red_rose_vale, Nationality.SWISS, EngineConstructor.Ferrari, new NonDriverPerson("Alessandro Alunni", "Bravi", Nationality.ITALIAN), new NonDriverPerson("Jan", "Monchaux", Nationality.FRENCH)), // Sauber
    ALPH("Scuderia AlphaTauri", R.color.black_charcoal, Nationality.ITALIAN, EngineConstructor.RBPT, new NonDriverPerson("Franz", "Tost", Nationality.AUSTRIAN), new NonDriverPerson("Jody", "Egginton", Nationality.BRITISH)), // Toro Rosso
    ALPI("Alpine F1 Team", R.color.purple_middle, Nationality.FRENCH, EngineConstructor.Renault, new NonDriverPerson("Bruno", "Famin", Nationality.FRENCH)), // Renault
    ASTO("Aston Martin F1 Team", R.color.green_viridian, Nationality.BRITISH, EngineConstructor.Mercedes, new NonDriverPerson("Mike", "Krack", Nationality.LUXEMBOURGISH), new NonDriverPerson("Dan", "Fallows", Nationality.BRITISH)), // Racing Point
    WILL("Williams Racing", R.color.blue_lapis_lazuli, Nationality.BRITISH, EngineConstructor.Mercedes, new NonDriverPerson("James", "Vowles", Nationality.BRITISH), new NonDriverPerson("Pat", "Fry", Nationality.BRITISH)), // Williams
    MCLA("McLaren Racing", R.color.orange_persian, Nationality.BRITISH, EngineConstructor.Mercedes, new NonDriverPerson("Andrea", "Stella", Nationality.ITALIAN)), // McLaren
    REDB("Red Bull Racing", R.color.blue_prussian, Nationality.AUSTRIAN, EngineConstructor.RBPT, new NonDriverPerson("Christian", "Horner", Nationality.BRITISH), new NonDriverPerson("Pierre", "Waché", Nationality.FRENCH)),     // RedBull
    MANO("Marussia F1 Team", R.color.red_english_vermillion, Nationality.BRITISH, EngineConstructor.Ferrari, new NonDriverPerson("Dave", "Ryan", Nationality.AUSTRALIAN)), // Virgin Racing
    LOTU("Lotus F1", R.color.yellow_metallic_gold, Nationality.BRITISH, EngineConstructor.Renault, new NonDriverPerson("Gerard", "Lopez", Nationality.LUXEMBOURGISH)), // Lotus Renault
    FORC("Sahare Force India F1 Team", R.color.purple_middle, Nationality.INDIAN, EngineConstructor.Mercedes, new NonDriverPerson("Vijay", "Mallya", Nationality.INDIAN)), // Force India
    DEFAULT();

    private final String name;
    private final int colorId;
    private final Nationality nationality;
    private final EngineConstructor engine;

    private final NonDriverPerson teamLead;

    private final NonDriverPerson technicalDirector;

    ConstructorAttr() {
        TypedValue color = new TypedValue();
        MainActivity.getAppContext().getTheme().resolveAttribute(com.google.android.material.R.attr.colorOnSecondary, color, true);
        this.name = "NaN";
        this.colorId = color.resourceId;
        this.nationality = Nationality.DEFAULT;
        this.engine = null;
        this.teamLead = null;
        this.technicalDirector = null;
    }

    ConstructorAttr(String name, int colorId, Nationality nationality, EngineConstructor engine, NonDriverPerson teamLead) {
        this.name = name;
        this.colorId = colorId;
        this.nationality = nationality;
        this.engine = engine;
        this.teamLead = teamLead;
        this.technicalDirector = null;
    }

    ConstructorAttr(String name, int colorId, Nationality nationality, EngineConstructor engine, NonDriverPerson teamLead, NonDriverPerson technicalDirector) {
        this.name = name;
        this.colorId = colorId;
        this.nationality = nationality;
        this.engine = engine;
        this.teamLead = teamLead;
        this.technicalDirector = technicalDirector;
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

    public NonDriverPerson getTeamLead() {
        return teamLead;
    }

    public NonDriverPerson getTechnicalDirector() {
        return technicalDirector;
    }

    public static ConstructorAttr getConstructorByName(String name) {
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
        return ConstructorAttr.getConstructorByName(constructor.getName());
    }
}
