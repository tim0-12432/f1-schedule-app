package de.tim0_12432.f1_schedule_app.data.entity;

import android.util.TypedValue;

import java.util.Locale;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public enum ConstructorAttr {
    FERR("Scuderia Ferrari", R.color.red_english_vermillion, Nationality.ITALIAN, EngineConstructor.Ferrari, new NonDriverPerson("Frédéric", "Vasseur", Nationality.FRENCH), new NonDriverPerson("Enrico", "Cardile", Nationality.ITALIAN)), // Ferrari
    MERC("Mercedes-AMG Petronas Motorsport", "Mercedes", R.color.green_keppel, Nationality.GERMAN, EngineConstructor.Mercedes, new NonDriverPerson("Toto", "Wolff", Nationality.AUSTRIAN), new NonDriverPerson("James", "Allison", Nationality.BRITISH)), // Mercedes
    HAAS("MoneyGram Haas F1 Team", "Haas", R.color.blue_yonder, Nationality.AMERICAN, EngineConstructor.Ferrari, new NonDriverPerson("Ayao", "Komatsu", Nationality.JAPANESE), new NonDriverPerson("Rob", "Taylor", Nationality.BRITISH)), // Haas
    SAUB("Stake F1 Team", "Sauber", R.color.green_pantone, Nationality.SWISS, EngineConstructor.Ferrari, new NonDriverPerson("Alessandro Alunni", "Bravi", Nationality.ITALIAN), new NonDriverPerson("James", "Key", Nationality.BRITISH)), // Sauber
    RBF1("Visa Cash App RB Formula One Team", "Racing Bulls", R.color.blue_cerulean, Nationality.ITALIAN, EngineConstructor.RBPT, new NonDriverPerson("Laurent", "Mekkies", Nationality.FRENCH), new NonDriverPerson("Jody", "Egginton", Nationality.BRITISH)), // Toro Rosso
    ALPI("Alpine F1 Team", R.color.purple_middle, Nationality.FRENCH, EngineConstructor.Renault, new NonDriverPerson("Bruno", "Famin", Nationality.FRENCH), new NonDriverPerson("Matthew", "Harman", Nationality.BRITISH)), // Renault
    ASTO("Aston Martin F1 Team", R.color.green_viridian, Nationality.BRITISH, EngineConstructor.Mercedes, new NonDriverPerson("Mike", "Krack", Nationality.LUXEMBOURGISH), new NonDriverPerson("Dan", "Fallows", Nationality.BRITISH)), // Racing Point
    WILL("Williams Racing", R.color.blue_lapis_lazuli, Nationality.BRITISH, EngineConstructor.Mercedes, new NonDriverPerson("James", "Vowles", Nationality.BRITISH), new NonDriverPerson("Pat", "Fry", Nationality.BRITISH)), // Williams
    MCLA("McLaren Racing", R.color.orange_persian, Nationality.BRITISH, EngineConstructor.Mercedes, new NonDriverPerson("Andrea", "Stella", Nationality.ITALIAN)), // McLaren
    REDB("Oracle Red Bull Racing", "Red Bull", R.color.blue_prussian, Nationality.AUSTRIAN, EngineConstructor.RBPT, new NonDriverPerson("Christian", "Horner", Nationality.BRITISH), new NonDriverPerson("Pierre", "Waché", Nationality.FRENCH)),     // RedBull
    MANO("Marussia F1 Team", R.color.red_english_vermillion, Nationality.BRITISH, EngineConstructor.Ferrari, new NonDriverPerson("Dave", "Ryan", Nationality.AUSTRALIAN)), // Virgin Racing
    LOTU("Lotus F1", R.color.yellow_metallic_gold, Nationality.BRITISH, EngineConstructor.Renault, new NonDriverPerson("Gerard", "Lopez", Nationality.LUXEMBOURGISH)), // Lotus Renault
    FORC("Sahare Force India F1 Team", R.color.purple_middle, Nationality.INDIAN, EngineConstructor.Mercedes, new NonDriverPerson("Vijay", "Mallya", Nationality.INDIAN)), // Force India
    DEFAULT();

    private final String name;

    private final String alterativeName;

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
        this.alterativeName = null;
    }

    ConstructorAttr(String name, int colorId, Nationality nationality, EngineConstructor engine, NonDriverPerson teamLead) {
        this.name = name;
        this.colorId = colorId;
        this.nationality = nationality;
        this.engine = engine;
        this.teamLead = teamLead;
        this.technicalDirector = null;
        this.alterativeName = null;
    }

    ConstructorAttr(String name, String alterativeName, int colorId, Nationality nationality, EngineConstructor engine, NonDriverPerson teamLead) {
        this.name = name;
        this.colorId = colorId;
        this.nationality = nationality;
        this.engine = engine;
        this.teamLead = teamLead;
        this.technicalDirector = null;
        this.alterativeName = alterativeName;
    }

    ConstructorAttr(String name, int colorId, Nationality nationality, EngineConstructor engine, NonDriverPerson teamLead, NonDriverPerson technicalDirector) {
        this.name = name;
        this.colorId = colorId;
        this.nationality = nationality;
        this.engine = engine;
        this.teamLead = teamLead;
        this.technicalDirector = technicalDirector;
        this.alterativeName = null;
    }

    ConstructorAttr(String name, String alternativeName, int colorId, Nationality nationality, EngineConstructor engine, NonDriverPerson teamLead, NonDriverPerson technicalDirector) {
        this.name = name;
        this.colorId = colorId;
        this.nationality = nationality;
        this.engine = engine;
        this.teamLead = teamLead;
        this.technicalDirector = technicalDirector;
        this.alterativeName = alternativeName;
    }

    public int getColor() {
        if (colorId == 0) {
            return MainActivity.getAppResources().getColor(R.color.black_jet);
        }
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

    public String getAlterativeName() {
        return alterativeName;
    }

    public static ConstructorAttr getConstructorByName(String name) {
        try {
            return ConstructorAttr.valueOf(name.replace(" ", "").toUpperCase(Locale.ROOT).substring(0, 4));
        } catch (IllegalArgumentException e) {
            Logger.log(Logger.LogLevel.WARN, "No constructor found for " + name + "!");
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
