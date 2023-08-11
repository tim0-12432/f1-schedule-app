package de.tim0_12432.f1_schedule_app.data.analysis;

import static java.util.stream.Collectors.toList;

import android.content.Context;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import de.tim0_12432.f1_schedule_app.data.analysis.chart.AccidentChart;
import de.tim0_12432.f1_schedule_app.data.analysis.chart.PointsChart;
import de.tim0_12432.f1_schedule_app.data.analysis.chart.PositionChart;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorAttr;
import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class ChartService {

    private final Context appContext;

    private final AnalysisService analysisService;

    public ChartService(Context context, AnalysisService analysisService) {
        this.appContext = context;
        this.analysisService = analysisService;
    }

    private int getColorForDriver(DriverStanding driverStanding) {
        ConstructorAttr attributes = ConstructorAttr.getConstructorOfTeam(driverStanding.getConstructor());
        return attributes.getColor();
    }

    public void createAccidentsChart(BarChart chart) {
        Map<DriverStanding, Integer> data = analysisService.getAccidentsPerDriver();

        if (data.isEmpty()) {
            return;
        }

        List<BarEntry> chartData = IntStream.range(0, data.size())
                .mapToObj(i -> {
                    DriverStanding key = (DriverStanding) data.keySet().toArray()[i];
                    int value = data.getOrDefault(key, 0);
                    return new BarEntry(i, value);
                })
                .collect(toList());

        BarDataSet dataSet = new BarDataSet(chartData, "Accidents");
        dataSet.setColors(data.keySet().stream().map(this::getColorForDriver).collect(toList()));
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });

        AccidentChart accidentChart = new AccidentChart(appContext, chart, dataSet, data);
        accidentChart.create();
    }

    public void createFrontRowChart(BarChart chart) {
        Map<DriverStanding, Integer> data = analysisService.getFrontRowPerDriver();

        if (data.isEmpty()) {
            return;
        }

        List<BarEntry> chartData = IntStream.range(0, data.size())
                .mapToObj(i -> {
                    DriverStanding key = (DriverStanding) data.keySet().toArray()[i];
                    int value = data.getOrDefault(key, 0);
                    return new BarEntry(i, value);
                })
                .collect(toList());

        BarDataSet dataSet = new BarDataSet(chartData, "TimesInFrontRow");
        dataSet.setColors(data.keySet().stream().map(this::getColorForDriver).collect(toList()));
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });

        AccidentChart accidentChart = new AccidentChart(appContext, chart, dataSet, data);
        accidentChart.create();
    }

    public void createPointsChart(LineChart chart) {
        Map<DriverStanding, Integer[]> data = analysisService.getPointsPerDriver();

        if (data.isEmpty()) {
            return;
        }

        List<DataSet> dataSets = new ArrayList<>();
        for (DriverStanding driver : data.keySet()) {
            Integer[] points = data.get(driver);
            final Integer[] memory = {0};
            List<Entry> chartData = IntStream.range(0, points.length)
                    .mapToObj(i -> {
                        if (points[i] == null) {
                            return new Entry(i, memory[0]);
                        }
                        int p = points[i] + memory[0];
                        memory[0] = p;
                        return new Entry(i, p);
                    })
                    .collect(toList());

            LineDataSet dataSet = new LineDataSet(chartData, driver.getDriver().getCode());
            dataSet.setColors(getColorForDriver(driver));
            dataSet.setCircleColors(getColorForDriver(driver));
            dataSet.setCircleRadius(3f);
            dataSet.setLineWidth(2f);
            dataSet.setDrawValues(true);
            dataSet.setLabel(driver.getDriver().getCode());
            dataSet.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return String.valueOf((int) value);
                }
            });
            dataSets.add(dataSet);
        }

        PointsChart pointsChart = new PointsChart(
                appContext,
                chart,
                analysisService.getMaximumPossiblePoints(),
                dataSets.toArray(new DataSet[]{}));
        pointsChart.create();
    }

    public void createPositionChart(LineChart chart) {
        Map<DriverStanding, Integer[]> data = analysisService.getPositionHistoryPerDriver();

        if (data.isEmpty()) {
            return;
        }

        List<DataSet> dataSets = new ArrayList<>();
        for (DriverStanding driver : data.keySet()) {
            Integer[] positions = data.get(driver);
            List<Entry> chartData = IntStream.range(0, positions.length)
                    .mapToObj(i -> {
                        if (positions[i] == null) {
                            return new Entry(i, data.size());
                        }
                        return new Entry(i, positions[i]);
                    })
                    .collect(toList());

            LineDataSet dataSet = new LineDataSet(chartData, driver.getDriver().getCode());
            dataSet.setColors(getColorForDriver(driver));
            dataSet.setDrawCircles(false);
            dataSet.setLineWidth(2f);
            dataSet.setDrawValues(true);
            dataSet.setLabel(driver.getDriver().getCode());
            dataSet.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return String.valueOf((int) value);
                }
            });
            dataSets.add(dataSet);
        }

        PositionChart positionChart = new PositionChart(
                appContext,
                chart,
                dataSets.toArray(new DataSet[]{}));
        positionChart.create();
    }
}
