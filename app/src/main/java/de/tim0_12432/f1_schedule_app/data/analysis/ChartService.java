package de.tim0_12432.f1_schedule_app.data.analysis;

import android.content.Context;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.SingleValueDataSet;
import com.anychart.charts.CircularGauge;
import com.anychart.core.axes.Circular;
import com.anychart.core.gauge.pointers.Bar;
import com.anychart.enums.Anchor;
import com.anychart.graphics.vector.Fill;
import com.anychart.graphics.vector.SolidFill;
import com.anychart.graphics.vector.text.HAlign;
import com.anychart.graphics.vector.text.VAlign;

import java.util.Map;

import de.tim0_12432.f1_schedule_app.utility.Logger;

public class ChartService {

    private Context appContext;

    private AnalysisService analysisService;

    public ChartService(Context context, AnalysisService analysisService) {
        this.appContext = context;
        this.analysisService = analysisService;
    }

    public void createTotalPointsChart(AnyChartView anyChartView) {
        if (anyChartView == null) {
            Logger.log(Logger.LogLevel.ERROR, "AnyChartView is null!");
            return;
        }
        APIlib.getInstance().setActiveAnyChartView(anyChartView);
        Map<String, Integer> totalPoints = analysisService.getPointsPerDriver();

        CircularGauge circularGauge = AnyChart.circular();
        circularGauge.data(new SingleValueDataSet(totalPoints.values().toArray(new Integer[0])));
        circularGauge.fill("#fff")
                .stroke(null)
                .padding(0d, 0d, 0d, 0d)
                .margin(100d, 100d, 100d, 100d);
        circularGauge.startAngle(0d);
        circularGauge.sweepAngle(270d);

        double radius = totalPoints.size() * 20d;
        double maximum = analysisService.getMaximumPossiblePoints();
        Circular xAxis = circularGauge.axis(0)
                .radius(radius)
                .width(1d)
                .fill((Fill) null);
        xAxis.scale()
                .minimum(0d)
                .maximum(maximum);
        xAxis.ticks("{ interval: 1 }")
                .minorTicks("{ interval: 1 }");
        xAxis.labels().enabled(false);
        xAxis.ticks().enabled(false);
        xAxis.minorTicks().enabled(false);

        for (int i = 0; i < totalPoints.size(); i++) {
            String driverCode = totalPoints.keySet().toArray(new String[0])[i];

            circularGauge.label(0d)
                    .text(driverCode + " " + totalPoints.get(driverCode))
                    .useHtml(true)
                    .hAlign(HAlign.CENTER)
                    .vAlign(VAlign.MIDDLE);
            circularGauge.label(1d * i)
                    .anchor(Anchor.RIGHT_CENTER)
                    .padding(0d, 10d, 0d, 0d)
                    .height(17d / 2d + "%")
                    .offsetY((radius - 20d * i) + "%")
                    .offsetX(0d);
            Bar bar = circularGauge.bar(1d * i);
            bar.dataIndex(1d * i);
            bar.radius(radius - 20d * i);
            bar.width(17d);
            bar.fill(new SolidFill("#64b5f6", 1d));
            bar.stroke(null);
            bar.zIndex(5d);
            Bar barAlt = circularGauge.bar(radius + 1d * i);
            barAlt.dataIndex(radius + 1d * i);
            barAlt.radius(radius - 20d * i);
            barAlt.width(17d);
            barAlt.fill(new SolidFill("#F5F4F4", 1d));
            barAlt.stroke("1 #e5e4e4");
            barAlt.zIndex(4d);
        }
        circularGauge.margin(50d, 50d, 50d, 50d);
        circularGauge.title()
                .text("<small>possible points:</small> " + maximum)
                .useHtml(true)
                .padding(0d, 0d, 0d, 0d)
                .margin(10d, 0d, 20d, 0d);
        circularGauge.title().enabled(true);
        circularGauge.title().hAlign(HAlign.CENTER).vAlign(VAlign.BOTTOM);

        anyChartView.setChart(circularGauge);
        circularGauge.draw(true);
    }
}
