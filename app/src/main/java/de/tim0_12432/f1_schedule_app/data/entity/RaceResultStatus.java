package de.tim0_12432.f1_schedule_app.data.entity;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public enum RaceResultStatus {
    FINISHED(1, R.string.finished, "\uD83C\uDFC1"), // 🏁
    DISQUALIFIED(2, R.string.disqualified, "\uD83C\uDF8C"), // 🏂
    ACCIDENT(3, R.string.accident, "\uD83D\uDEA9"), // 🚩
    COLLISION(4, R.string.collision, "\uD83D\uDEA9"), // 🚩
    ENGINE(5, R.string.engine),
    GEARBOX(6, R.string.gearbox),
    TRANSMISSION(7, R.string.transmission),
    CLUTCH(8, R.string.clutch),
    HYDRAULICS(9, R.string.hydraulics),
    ELECTRICAL(10, R.string.electrical),
    PLUS_1_LAP(11, R.string.plus_one_lap, "\uD83C\uDFF3"), // 🏳
    PLUS_2_LAPS(12, R.string.plus_two_laps, "\uD83C\uDFF3"), // 🏳
    PLUS_3_LAPS(13, R.string.plus_three_laps, "\uD83C\uDFF3"), // 🏳
    PLUS_4_LAPS(14, R.string.plus_four_laps, "\uD83C\uDFF3"), // 🏳
    PLUS_5_LAPS(15, R.string.plus_five_laps, "\uD83C\uDFF3"), // 🏳
    PLUS_6_LAPS(16, R.string.plus_six_laps, "\uD83C\uDFF3"), // 🏳
    PLUS_7_LAPS(17, R.string.plus_seven_laps, "\uD83C\uDFF3"), // 🏳
    SPUN_OFF(20, R.string.spun_off),
    RADIATOR(21, R.string.radiator),
    SUSPENSION(22, R.string.suspension),
    BRAKES(23, R.string.brakes),
    DIFFERENTIAL(24, R.string.differential),
    OVERHEATING(25, R.string.overheating),
    MECHANICAL(26, R.string.mechanical),
    TYRE(27, R.string.tyre),
    DRIVER_SEAT(28, R.string.driver_seat),
    PUNCTURE(29, R.string.puncture, "\uD83D\uDEA9"), // 🚩
    DRIVE_SHAFT(30, R.string.driveshaft),
    RETIRED(31, R.string.retired, "\uD83C\uDF8C"), // 🏂
    WATER_PRESSURE(34, R.string.water_pressure),
    WHEEL(36, R.string.wheel), // 🚩
    EXHAUST(43, R.string.exhaust),
    OIL_LEAK(44, R.string.oil_leak),
    WITHDREW(54, R.string.withdrew, "\uD83C\uDF8C"), // 🏂
    OUT_OF_FUEL(60, R.string.out_of_fuel),
    POWER_LOSS(75, R.string.power_loss),
    COLLISION_DAMAGE(130, R.string.collision_damage, "\uD83D\uDEA9"), // 🚩
    POWER_UNIT(131, R.string.power_unit),
    DAMAGE(137, R.string.damage, "\uD83D\uDEA9"), // 🚩
    DEFAULT();

    private final int code;
    private final String text;
    private final String emoji;

    RaceResultStatus() {
        this(-1, R.string.status_unknown);
    }

    RaceResultStatus(int code, int stringId) {
        this(code, stringId, "\uD83C\uDFF4");
    } // 🏴

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
