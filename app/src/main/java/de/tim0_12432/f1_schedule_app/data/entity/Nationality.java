package de.tim0_12432.f1_schedule_app.data.entity;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public enum Nationality {
    AMERICAN("\uD83C\uDDFA\uD83C\uDDF8", R.string.nationality_american, "USA"),
    ARAB("\uD83C\uDDE6\uD83C\uDDEA", R.string.nationality_arab, "UAE"),
    AUSTRIAN("\uD83C\uDDE6\uD83C\uDDF9", R.string.nationality_austrian, "Austria"),
    AUSTRALIAN("\uD83C\uDDE6\uD83C\uDDFA", R.string.nationality_australian, "Australia"),
    AZERBAIJANI("\uD83C\uDDE6\uD83C\uDDFF", R.string.nationality_azerbaijani, "Azerbaijan"),
    BAHRAINI("\uD83C\uDDE7\uD83C\uDDED", R.string.nationality_bahraini, "Bahrain"),
    BELGIAN("\uD83C\uDDE7\uD83C\uDDEA", R.string.nationality_belgian, "Belgium"),
    BRAZILIAN("\uD83C\uDDE7\uD83C\uDDF7", R.string.nationality_brazilian, "Brazil"),
    BRITISH("\uD83C\uDDEC\uD83C\uDDE7", R.string.nationality_british, "UK"),
    CANADIAN("\uD83C\uDDE8\uD83C\uDDE6", R.string.nationality_canadian, "Canada"),
    CHINESE("\uD83C\uDDE8\uD83C\uDDF3", R.string.nationality_chinese, "China"),
    DANISH("\uD83C\uDDE9\uD83C\uDDF0", R.string.nationality_danish, "Denmark"),
    DUTCH("\uD83C\uDDF3\uD83C\uDDF1", R.string.nationality_dutch, "Netherlands"),
    FINNISH("\uD83C\uDDEB\uD83C\uDDEE", R.string.nationality_finnish, "Finland"),
    FRENCH("\uD83C\uDDEB\uD83C\uDDF7", R.string.nationality_french, "France"),
    GERMAN("\uD83C\uDDE9\uD83C\uDDEA", R.string.nationality_german, "Germany"),
    HUNGARIAN("\uD83C\uDDED\uD83C\uDDFA", R.string.nationality_hungarian, "Hungary"),
    ITALIAN("\uD83C\uDDEE\uD83C\uDDF9", R.string.nationality_italian, "Italy"),
    JAPANESE("\uD83C\uDDEF\uD83C\uDDF5", R.string.nationality_japanese, "Japan"),
    MEXICAN("\uD83C\uDDF2\uD83C\uDDFD", R.string.nationality_mexican, "Mexico"),
    MONEGASQUE("\uD83C\uDDF2\uD83C\uDDE8", R.string.nationality_monegasque, "Monaco"),
    RUSSIAN("\uD83C\uDDF7\uD83C\uDDFA", R.string.nationality_russian, "Russia"),
    SAUDI("\uD83C\uDDF8\uD83C\uDDE6", R.string.nationality_saudi, "Saudi Arabia"),
    SINGAPOREAN("\uD83C\uDDF8\uD83C\uDDEC", R.string.nationality_singaporean, "Singapore"),
    SPANISH("\uD83C\uDDEA\uD83C\uDDF8", R.string.nationality_spanish, "Spain"),
    SWISS("\uD83C\uDDE8\uD83C\uDDED", R.string.nationality_swiss, "Switzerland"),
    THAI("\uD83C\uDDF9\uD83C\uDDED", R.string.nationality_thai, "Thailand"),
    DEFAULT();

    private final String emojiFlag;
    private final int translationId;
    private final String country;

    Nationality() {
        emojiFlag = "\uD83C\uDFF3\u200D\uD83C\uDF08";
        translationId = R.string.nationality_default;
        country = MainActivity.getAppResources().getString(R.string.nationality_default);
    }

    Nationality(String emojiFlag) {
        this.emojiFlag = emojiFlag;
        translationId = R.string.nationality_default;
        country = MainActivity.getAppResources().getString(R.string.nationality_default);
    }

    Nationality(int translationId, String country) {
        emojiFlag = "\uD83C\uDFF3\u200D\uD83C\uDF08";
        this.translationId = translationId;
        this.country = country;
    }

    Nationality(String emojiFlag, int translationId, String country){
        this.emojiFlag = emojiFlag;
        this.translationId = translationId;
        this.country = country;
    }

    public String getEmojiFlag() {
        return emojiFlag;
    }

    public String getTranslation() {
        return MainActivity.getAppResources().getString(translationId);
    }

    public int getTranslationId() {
        return translationId;
    }

    public String getCountry() {
        return country;
    }

    public static Nationality getNationalityOfTranslation(String englishTranslation) {
        try {
            return Nationality.valueOf(englishTranslation.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            Resources currUsed = MainActivity.getAppResources();
            Configuration config = currUsed.getConfiguration();
            config.locale = new Locale("en");
            Resources englishResources = new Resources(currUsed.getAssets(), currUsed.getDisplayMetrics(), config);
            for (Nationality nationality : values()) {
                String actualTranslation = englishResources.getString(nationality.getTranslationId());
                if (actualTranslation.equals(englishTranslation)) {
                    return nationality;
                }
            }
            Logger.log(Logger.LogLevel.WARN, "No nationality for translation", englishTranslation, "!");
            return DEFAULT;
        }
    }

    public static Nationality getNationalityOfCountry(String country) {
        for (Nationality nationality : values()) {
            if (nationality.getCountry().equals(country)) {
                return nationality;
            }
        }
        Logger.log(Logger.LogLevel.WARN, "No nationality for country", country, "!");
        return DEFAULT;
    }
}
