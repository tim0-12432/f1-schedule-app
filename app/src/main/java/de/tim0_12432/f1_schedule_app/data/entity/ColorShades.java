package de.tim0_12432.f1_schedule_app.data.entity;

import java.util.Arrays;
import java.util.List;

import de.tim0_12432.f1_schedule_app.R;

public class ColorShades {
    public static final int[] dark = {R.color.blue_prussian, R.color.black_charcoal, R.color.black_raisin_dark, R.color.black_xiketic_dark};
    public static final int[] green = {R.color.green_keppel, R.color.green_pantone, R.color.green_viridian};
    public static final int[] red = {R.color.red_english_vermillion, R.color.red_rose_vale, R.color.red_cg, R.color.red_indian};
    public static final int[] pink = {R.color.purple_purpureus, R.color.purple_middle};
    public static final int[] blue = {R.color.blue_ncs, R.color.blue_yonder, R.color.blue_lapis_lazuli};
    public static final int[] yellow = {R.color.yellow_metallic_gold, R.color.orange_persian};

    public static final List<int[]> shades = Arrays.asList(dark, green, red, pink, blue, yellow);

    public static int[] getShadesForColor(int color) {
        for (int[] shade : shades) {
            for (int c : shade) {
                if (c == color) {
                    return shade;
                }
            }
        }
        return dark;
    }
}
