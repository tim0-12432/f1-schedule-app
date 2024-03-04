package de.tim0_12432.f1_schedule_app.data.analysis.chart;

import android.content.Context;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.Arrays;
import java.util.List;

import de.tim0_12432.f1_schedule_app.R;

public class DriverComparisonChart extends AbstractChart<BarChart> {

    public DriverComparisonChart(Context ctx, BarChart chart, List<DataSet> dataSetList) {
        super(ctx, chart, dataSetList.toArray(new DataSet[0]));
    }

    @Override
    public void create() {
        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setAxisMaximum(Arrays.stream(dataSet).map(DataSet::getXMax).max(Float::compareTo).orElse(20f));
        yAxisLeft.setDrawGridLinesBehindData(true);
        yAxisLeft.setGridColor(getThemedColorFor(ColorElement.GRID));
        yAxisLeft.setGridLineWidth(1);
        yAxisLeft.setTextColor(getThemedColorFor(ColorElement.LABEL));
        yAxisLeft.setDrawGridLines(true);
        yAxisLeft.setDrawAxisLine(false);
        yAxisLeft.setDrawLabels(false);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setEnabled(false);

        BarData barData = new BarData((IBarDataSet) dataSet[0]);
        barData.setValueTextColor(getThemedColorFor(ColorElement.LABEL));
        chart.setData(barData);

        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setDrawAxisLine(true);
        chart.getXAxis().setTextColor(getThemedColorFor(ColorElement.LABEL));
        chart.getXAxis().setAxisLineWidth(2);
        chart.getXAxis().setAxisLineColor(getThemedColorFor(ColorElement.AXIS));
        chart.getXAxis().setLabelCount(dataSet.length);
        chart.getXAxis().setLabelRotationAngle(90);
        chart.getXAxis().setGranularity(1);
        chart.getXAxis().setGranularityEnabled(true);
        chart.animateY(1000, Easing.EaseInOutSine);
        chart.setNoDataTextColor(getThemedColorFor(ColorElement.ERROR));
        chart.setNoDataText(appContext.getString(R.string.no_data_for_chart));
        chart.invalidate();
    }
}
