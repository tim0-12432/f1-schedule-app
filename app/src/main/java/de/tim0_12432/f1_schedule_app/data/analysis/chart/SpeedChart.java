package de.tim0_12432.f1_schedule_app.data.analysis.chart;

import static java.util.stream.Collectors.toList;

import android.content.Context;
import android.graphics.Paint;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.Map;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.analysis.AnalysisService;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;

public class SpeedChart extends AbstractChart<CandleStickChart> {

    private final Map<DriverStanding, AnalysisService.CandleStick> data;

    public SpeedChart(Context ctx, CandleStickChart chart, DataSet dataSet, Map<DriverStanding, AnalysisService.CandleStick> data) {
        super(ctx, chart, dataSet);
        this.data = data;
    }

    @Override
    public void create() {
        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setAxisMinimum(data.values().stream().map(r -> r.close - 5).min(Float::compareTo).orElse(350f));
        yAxisLeft.setAxisMaximum(data.values().stream().map(r -> r.high + 5).max(Float::compareTo).orElse(400f));
        yAxisLeft.setDrawGridLinesBehindData(true);
        yAxisLeft.setLabelCount(5);
        yAxisLeft.setGridColor(getThemedColorFor(ColorElement.GRID));
        yAxisLeft.setGridLineWidth(1);
        yAxisLeft.setTextColor(getThemedColorFor(ColorElement.LABEL));
        yAxisLeft.setAxisLineWidth(2);
        yAxisLeft.setDrawGridLines(true);
        yAxisLeft.setDrawAxisLine(true);
        yAxisLeft.setDrawLabels(true);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setEnabled(false);

        CandleDataSet candleDataSet = (CandleDataSet) dataSet[0];
        candleDataSet.setNeutralColor(getThemedColorFor(ColorElement.LABEL));
        candleDataSet.setDecreasingColor(getThemedColorFor(ColorElement.LABEL));
        candleDataSet.setIncreasingColor(getThemedColorFor(ColorElement.LABEL));
        candleDataSet.setShadowColor(getThemedColorFor(ColorElement.DATA));
        candleDataSet.setShadowWidth(7f);
        candleDataSet.setDecreasingPaintStyle(Paint.Style.FILL);
        candleDataSet.setIncreasingPaintStyle(Paint.Style.FILL);
        candleDataSet.setShowCandleBar(true);
        CandleData candleData = new CandleData(candleDataSet);
        candleData.setValueTextColor(getThemedColorFor(ColorElement.LABEL));
        chart.setData(candleData);

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
        chart.getXAxis().setTextColor(getThemedColorFor(ColorElement.LABEL));
        chart.getXAxis().setAxisLineWidth(2);
        chart.getXAxis().setAxisLineColor(getThemedColorFor(ColorElement.AXIS));
        chart.getXAxis().setLabelCount(data.size());
        chart.getXAxis().setLabelRotationAngle(90);
        chart.getXAxis().setGranularity(1);
        chart.getXAxis().setGranularityEnabled(true);
        chart.animateY(1000, Easing.EaseInOutSine);
        chart.setNoDataTextColor(getThemedColorFor(ColorElement.ERROR));
        chart.setNoDataText(appContext.getString(R.string.no_data_for_chart));
        chart.invalidate();
    }
}
