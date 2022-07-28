package de.tim0_12432.f1_schedule_app.data.entity;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;

public enum Nationality {
    AUSTRIAN("\uD83C\uDDE6\uD83C\uDDF9"),
    AUSTRALIAN("\uD83C\uDDE6\uD83C\uDDFA", R.string.nationality_australian, "Australia"),
    BRITISH("\uD83C\uDDEC\uD83C\uDDE7", R.string.nationality_british, "Great Britain"),
    CANADIAN("\uD83C\uDDE8\uD83C\uDDE6", R.string.nationality_canadian, "Canada"),
    DANISH("\uD83C\uDDE9\uD83C\uDDF0", R.string.nationality_danish, "Denmark"),
    DUTCH("\uD83C\uDDF3\uD83C\uDDF1", R.string.nationality_dutch, "Netherlands"),
    FINNISH("\uD83C\uDDEB\uD83C\uDDEE", R.string.nationality_finnish, "Finland"),
    FRENCH("\uD83C\uDDEB\uD83C\uDDF7", R.string.nationality_french, "France"),
    GERMAN("\uD83C\uDDE9\uD83C\uDDEA", R.string.nationality_german, "Germany"),
    ITALIAN("\uD83C\uDDEE\uD83C\uDDF9", R.string.nationality_italian, "Italy"),
    MEXICAN("\uD83C\uDDF2\uD83C\uDDFD", R.string.nationality_mexican, "Mexico"),
    MONEGASQUE("\uD83C\uDDF2\uD83C\uDDE8", R.string.nationality_monegasque, "Monaco"),
    RUSSIAN("\uD83C\uDDF7\uD83C\uDDFA", R.string.nationality_russian, "Russia"),
    SPANISH("\uD83C\uDDEA\uD83C\uDDF8", R.string.nationality_spanish, "Spain"),
    THAI("\uD83C\uDDF9\uD83C\uDDED", R.string.nationality_thai, "Thailand"),
    DEFAULT();

    private final String emojiFlag;
    private final String translation;
    private final String country;

    Nationality() {
        emojiFlag = "\uD83C\uDFF3";
        translation = MainActivity.getAppResources().getString(R.string.nationality_default);
        country = MainActivity.getAppResources().getString(R.string.nationality_default);
    }

    Nationality(String emojiFlag) {
        this.emojiFlag = emojiFlag;
        translation = MainActivity.getAppResources().getString(R.string.nationality_default);
        country = MainActivity.getAppResources().getString(R.string.nationality_default);
    }

    Nationality(int translationId, String country) {
        emojiFlag = "\uD83C\uDFF3";
        this.translation = MainActivity.getAppResources().getString(translationId);
        this.country = country;
    }

    Nationality(String emojiFlag, int translationId, String country){
        this.emojiFlag = emojiFlag;
        this.translation = MainActivity.getAppResources().getString(translationId);
        this.country = country;
    }
}
