package de.tim0_12432.f1_schedule_app.data.analysis.chart;

import android.content.Context;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.DataSet;

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
}
