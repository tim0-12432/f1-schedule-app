package de.tim0_12432.f1_schedule_app.utility;

public class Speed {
    public static String convertMphToKmh(String speed) {
        if (speed == null || speed.isEmpty()) {
            return "0.0";
        }
        return String.valueOf(convertMphToKmh(Double.parseDouble(speed)));
    }

    public static String convertKmhToMph(String speed) {
        if (speed == null || speed.isEmpty()) {
            return "0.0";
        }
        return String.valueOf(convertKmhToMph(Double.parseDouble(speed)));
    }

    public static double convertMphToKmh(double speed) {
        return Math.round(speed * 1.61 * 10) / 10.0;
    }

    public static double convertKmhToMph(double speed) {
        return Math.round(speed * 0.62 * 10) / 10.0;
    }
}
