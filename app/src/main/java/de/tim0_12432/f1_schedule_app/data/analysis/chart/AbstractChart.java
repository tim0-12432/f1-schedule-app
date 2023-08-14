package de.tim0_12432.f1_schedule_app.data.analysis.chart;

import android.content.Context;
import android.content.res.Configuration;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.DataSet;

import de.tim0_12432.f1_schedule_app.R;

public abstract class AbstractChart<T extends Chart> {

    protected Context appContext;

    protected T chart;

    protected DataSet[] dataSet;

    public AbstractChart(Context ctx, T chart, DataSet... dataSet) {
        this.appContext = ctx;
        this.chart = chart;
        this.dataSet = dataSet;
    }

    public abstract void create();

    protected enum ColorElement {
        BACKGROUND, GRID, AXIS, LABEL, ERROR, DATA
    }

    protected int getThemedColorFor(ColorElement element) {
        if (element == ColorElement.ERROR) {
            return appContext.getColor(R.color.red_cg);
        }
        int config = appContext.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (config == Configuration.UI_MODE_NIGHT_YES) {
            return switch (element) {
                case BACKGROUND -> appContext.getColor(R.color.black_raisin_light);
                case GRID, AXIS -> appContext.getColor(R.color.black_raisin_dark);
                case DATA -> appContext.getColor(R.color.red_auburn);
                default -> appContext.getColor(R.color.silver_timberwolf);
            };
        } else if (config == Configuration.UI_MODE_NIGHT_NO) {
            return switch (element) {
                case BACKGROUND -> appContext.getColor(R.color.silver_timberwolf);
                case GRID, AXIS -> appContext.getColor(R.color.silver_metallic);
                case DATA -> appContext.getColor(R.color.red_auburn);
                default -> appContext.getColor(R.color.black_jet);
            };
        } else {
            return appContext.getColor(R.color.black_jet);
        }
    }
}
