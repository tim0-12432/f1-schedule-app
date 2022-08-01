package de.tim0_12432.f1_schedule_app.data.entity;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;

public enum RaceResultStatus {
    FINISHED(1, R.string.finished),
    ENGINE(5, R.string.engine),
    PLUS_1_LAP(11, R.string.plus_one_lap),
    PLUS_2_LAPS(12, R.string.plus_two_laps),
    PLUS_3_LAPS(13, R.string.plus_three_laps),
    WHEEL(36, R.string.wheel),
    DAMAGE(137, R.string.damage);

    private final int code;
    private final String text;

    RaceResultStatus(int code, int stringId) {
        this.code = code;
        this.text = MainActivity.getAppResources().getString(stringId);
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static RaceResultStatus getByCode(int code) {
        for (RaceResultStatus status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

    public static boolean finished(int code) {
        return code == 1 || (code >= 11 && code <= 13);
    }
}
