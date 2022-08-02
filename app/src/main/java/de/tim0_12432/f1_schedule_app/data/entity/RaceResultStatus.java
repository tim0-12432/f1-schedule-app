package de.tim0_12432.f1_schedule_app.data.entity;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public enum RaceResultStatus {
    FINISHED(1, R.string.finished, "\uD83C\uDFC1"),
    ENGINE(5, R.string.engine, "\uD83D\uDEA9"),
    PLUS_1_LAP(11, R.string.plus_one_lap, "\uD83C\uDFF3"),
    PLUS_2_LAPS(12, R.string.plus_two_laps, "\uD83C\uDFF3"),
    PLUS_3_LAPS(13, R.string.plus_three_laps, "\uD83C\uDFF3"),
    WHEEL(36, R.string.wheel, "\uD83D\uDEA9"),
    DAMAGE(137, R.string.damage),
    DEFAULT();

    private final int code;
    private final String text;
    private final String emoji;

    RaceResultStatus() {
        this(-1, R.string.status_unknown);
    }

    RaceResultStatus(int code, int stringId) {
        this(code, stringId, "\uD83C\uDFF4");
    }

    RaceResultStatus(int code, int stringId, String emoji) {
        this.code = code;
        this.text = MainActivity.getAppResources().getString(stringId);
        this.emoji = emoji;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public String getEmoji() {
        return emoji;
    }

    public static RaceResultStatus getByCode(int code) {
        for (RaceResultStatus status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        Logger.log("No status found for code " + code);
        return DEFAULT;
    }

    public static boolean finished(int code) {
        return code == 1 || (code >= 11 && code <= 13);
    }
}
