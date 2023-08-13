package de.tim0_12432.f1_schedule_app.data.analysis.chart;

import static java.util.stream.Collectors.toList;

import android.content.Context;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.Arrays;

import de.tim0_12432.f1_schedule_app.R;

public class PositionChart extends AbstractChart<LineChart> {

    public PositionChart(Context ctx, LineChart chart, DataSet... dataSet) {
        super(ctx, chart, dataSet);
    }

    @Override
    public void create() {
        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setAxisMinimum(0.5f);
        yAxisLeft.setAxisMaximum(dataSet.length + 0.5f);
        yAxisLeft.setLabelCount(10);
        yAxisLeft.setInverted(true);
        yAxisLeft.setDrawGridLinesBehindData(true);
        yAxisLeft.setGridColor(getThemedColorFor(ColorElement.GRID));
        yAxisLeft.setGridLineWidth(1);
        yAxisLeft.setTextColor(getThemedColorFor(ColorElement.LABEL));
        yAxisLeft.setDrawGridLines(true);
        yAxisLeft.setDrawAxisLine(false);
        yAxisLeft.setDrawLabels(true);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setEnabled(false);

        LineData lineData = new LineData(Arrays.stream(dataSet).map(d -> (ILineDataSet) d).collect(toList()));
        lineData.setValueTextColor(getThemedColorFor(ColorElement.LABEL));
        chart.setData(lineData);

        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(true);
        chart.getLegend().setDrawInside(false);
        chart.getLegend().setFormSize(4);
        chart.getLegend().setFormToTextSpace(2);
        chart.getLegend().setTextColor(getThemedColorFor(ColorElement.LABEL));
        chart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setDrawAxisLine(true);
        chart.getXAxis().setAxisLineWidth(2);
        chart.getXAxis().setAxisLineColor(getThemedColorFor(ColorElement.AXIS));
        chart.getXAxis().setLabelCount(dataSet[0].getEntryCount());
        chart.getXAxis().setGranularity(1);
        chart.getXAxis().setTextColor(getThemedColorFor(ColorElement.LABEL));
        chart.getXAxis().setGranularityEnabled(true);
        chart.animateY(1000, Easing.EaseInOutSine);
        chart.setNoDataTextColor(getThemedColorFor(ColorElement.ERROR));
        chart.setNoDataText(appContext.getString(R.string.no_data_for_chart));
        chart.invalidate();
    }
}
