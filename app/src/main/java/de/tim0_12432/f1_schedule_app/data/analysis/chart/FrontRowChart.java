package de.tim0_12432.f1_schedule_app.data.analysis.chart;

import static java.util.stream.Collectors.toList;

import android.content.Context;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.Map;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;

public class FrontRowChart extends AbstractChart<BarChart> {

    private final Map<DriverStanding, Integer> data;

    public FrontRowChart(Context ctx, BarChart chart, DataSet dataSet, Map<DriverStanding, Integer> data) {
        super(ctx, chart, dataSet);
        this.data = data;
    }

    @Override
    public void create() {
        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setAxisMaximum(data.values().stream().max(Integer::compareTo).orElse(20));
        yAxisLeft.setDrawGridLinesBehindData(true);
        yAxisLeft.setGridColor(appContext.getColor(R.color.silver_metallic));
        yAxisLeft.setGridLineWidth(1);
        yAxisLeft.setDrawGridLines(true);
        yAxisLeft.setDrawAxisLine(false);
        yAxisLeft.setDrawLabels(false);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setEnabled(false);

        BarData barData = new BarData((IBarDataSet) dataSet[0]);
        chart.setData(barData);

        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(
                data.keySet().stream()
                        .map(DriverStanding::getDriver)
                        .map(Driver::getCode)
                        .collect(toList())));
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setDrawAxisLine(true);
        chart.getXAxis().setAxisLineWidth(2);
        chart.getXAxis().setAxisLineColor(appContext.getColor(R.color.silver_metallic));
        chart.getXAxis().setLabelCount(data.size());
        chart.getXAxis().setLabelRotationAngle(90);
        chart.getXAxis().setGranularity(1);
        chart.getXAxis().setGranularityEnabled(true);
        chart.animateY(1000, Easing.EaseInOutSine);
        chart.setNoDataTextColor(appContext.getColor(R.color.red_cg));
        chart.setNoDataText(appContext.getString(R.string.no_data_for_chart));
        chart.invalidate();
    }
}
