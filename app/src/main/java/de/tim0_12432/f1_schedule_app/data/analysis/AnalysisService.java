package de.tim0_12432.f1_schedule_app.data.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.data.DataManager;
import de.tim0_12432.f1_schedule_app.data.Resource;
import de.tim0_12432.f1_schedule_app.data.entity.Constructor;
import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;
import de.tim0_12432.f1_schedule_app.data.entity.Qualifying;
import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResult;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultStatus;
import de.tim0_12432.f1_schedule_app.data.source.ILoadCallback;
import de.tim0_12432.f1_schedule_app.utility.DateTime;
import de.tim0_12432.f1_schedule_app.utility.Speed;

public class AnalysisService {

    private final List<Race> races = new ArrayList<>();
    private final List<Qualifying> qualifyings = new ArrayList<>();
    private final List<DriverStanding> driverStandings = new ArrayList<>();

    public AnalysisService() {
        DataManager manager = new DataManager(MainActivity.getAppContext());

        manager.getDataFrom(Resource.SCHEDULE, new ILoadCallback<Race>() {
            @Override
            public void onLoaded(List<Race> list) {
                List<Race> newRacesList = list.stream().map(Race::copyOf).collect(Collectors.toList());
                List<Qualifying> newQualifyingsList = new ArrayList<>();
                for (Race race : list) {
                    String raceUrl = race.getSeason() + "/" + race.getRound() + "/results";
                    manager.getDataFrom(race.getDate(), Resource.RACE_RESULTS, raceUrl, new ILoadCallback<Race>() {
                        @Override
                        public void onLoaded(List<Race> list) {
                            if (list.size() > 0) {
                                Race newRace = race;
                                newRace.addResults(list.get(0).getResults());
                                newRacesList.add(newRace);
                            }
                        }
                    });
                    String qualiUrl = race.getSeason() + "/" + race.getRound() + "/qualifying";
                    manager.getDataFrom(DateTime.plusDays(race.getDate(), -1), Resource.QUALIFYING_RESULTS, qualiUrl, new ILoadCallback<Qualifying>() {
                        @Override
                        public void onLoaded(List<Qualifying> list) {
                            if (list.size() > 0) {
                                newQualifyingsList.add(list.get(0));
                            }
                        }
                    });
                }
                races.addAll(newRacesList.stream()
                        .filter((Race race) -> race.getResults() != null && !race.getResults().getResults().isEmpty())
                        .collect(Collectors.toList()));
                qualifyings.addAll(newQualifyingsList.stream()
                        .filter((Qualifying quali) -> quali.getResults() != null && !quali.getResults().isEmpty())
                        .collect(Collectors.toList()));
            }
        });
        manager.getDataFrom(Resource.DRIVER_RANKING, new ILoadCallback<DriverStanding>() {
            @Override
            public void onLoaded(List<DriverStanding> list) {
                driverStandings.addAll(list);
            }
        });
    }

    public Integer getMaximumPossiblePoints() {
        return races.size() * 28;
    }

    public Map<DriverStanding, Integer> getAccidentsPerDriver() {
        final Map<DriverStanding, Integer> drivers = new HashMap<>();
        driverStandings.forEach(standing -> {
            drivers.put(standing, 0);
        });

        final Map<DriverStanding, Integer> result = new LinkedHashMap<>();
        drivers.entrySet().stream()
                .sorted((a, b) -> {
                    String cA = a.getKey().getConstructor().getName();
                    String cB = b.getKey().getConstructor().getName();
                    return cA.compareTo(cB);
                })
                .forEach(entry -> result.put(entry.getKey(), entry.getValue()));

        races.forEach(race -> race.getResults().getResults().forEach(entry -> {
            if (!(entry.getStatus().compareTo(RaceResultStatus.FINISHED) == 0
                    || (entry.getStatus().getCode() >= 11
                    && entry.getStatus().getCode() <= 17))) {
                driverStandings.stream()
                        .filter(driverStanding -> driverStanding.getDriver().getCode().equals(entry.getDriver().getCode()))
                        .findFirst().ifPresent(standing -> result.put(standing, result.get(standing) + 1));
            }
        }));
        return result;
    }

    public Map<DriverStanding, Integer> getFrontRowPerDriver() {
        final Map<DriverStanding, Integer> drivers = new HashMap<>();
        driverStandings.forEach(standing -> {
            drivers.put(standing, 0);
        });

        final Map<DriverStanding, Integer> result = new LinkedHashMap<>();
        drivers.entrySet().stream()
                .sorted((a, b) -> {
                    String cA = a.getKey().getConstructor().getName();
                    String cB = b.getKey().getConstructor().getName();
                    return cA.compareTo(cB);
                })
                .forEach(entry -> result.put(entry.getKey(), entry.getValue()));

        qualifyings.forEach(quali -> quali.getResults().forEach(entry -> {
            if (entry.getPosition() == 1 || entry.getPosition() == 2 || entry.getPosition() == 3) {
                driverStandings.stream()
                        .filter(driverStanding -> driverStanding.getDriver().getCode().equals(entry.getDriver().getCode()))
                        .findFirst().ifPresent(standing -> result.put(standing, result.get(standing) + 1));
            }
        }));
        return result;
    }

    public Map<DriverStanding, Integer[]> getPointsPerDriver() {
        final Map<DriverStanding, Integer[]> result = new HashMap<>();
        driverStandings.forEach(standing -> {
            Integer[] list = new Integer[races.size() + 1];
            list[0] = 0;
            result.put(standing, list);
        });

        IntStream.range(0, races.size()).forEach(i -> {
            Race race = races.get(i);
            race.getResults().getResults().forEach(entry -> {
                driverStandings.stream()
                        .filter(driverStanding -> driverStanding.getDriver().getCode().equals(entry.getDriver().getCode()))
                        .findFirst().ifPresent(standing -> Objects.requireNonNull(result.get(standing))[i + 1] = entry.getPoints());
            });
        });
        return result;
    }

    public Map<DriverStanding, Integer[]> getPositionHistoryPerDriver() {
        final Map<DriverStanding, Integer[]> result = new HashMap<>();
        driverStandings.forEach(standing -> {
            Integer[] list = new Integer[races.size()];
            result.put(standing, list);
        });

        IntStream.range(0, races.size()).forEach(i -> {
            Race race = races.get(i);
            race.getResults().getResults().forEach(entry -> {
                driverStandings.stream()
                        .filter(driverStanding -> driverStanding.getDriver().getCode().equals(entry.getDriver().getCode()))
                        .findFirst().ifPresent(standing -> Objects.requireNonNull(result.get(standing))[i] = entry.getPosition());
            });
        });
        return result;
    }

    public Map<DriverStanding, Integer[]> getComparisonOfDrivers() {
        final Map<DriverStanding, Integer[]> result = new HashMap<>();

        return result;
    }

    public static class CandleStick {
        public final float x;
        public final float open;
        public final float close;
        public final float high;
        public final float low;

        public CandleStick(float x, float open, float close, float high, float low) {
            this.x = x;
            this.open = open;
            this.close = close;
            this.high = high;
            this.low = low;
        }
    }

    public Map<DriverStanding, CandleStick> getFastestSpeedPerDriver() {
        final Map<DriverStanding, CandleStick> drivers = new HashMap<>();
        driverStandings.forEach(standing -> {
            drivers.put(standing, null);
        });

        driverStandings.forEach(driver -> {
            final float[] minSpeed = {Float.MAX_VALUE};
            final float[] maxSpeed = {Float.MIN_VALUE};
            final float[] avgSpeed = {0};
            final int[] count = {0};

            IntStream.range(0, races.size()).forEach(i -> {
                Race race = races.get(i);
                race.getResults().getResults().forEach(entry -> {
                    if (entry.getDriver().getCode().equals(driver.getDriver().getCode())) {
                        if (entry.getFastestLapSpeed() != null && !entry.getFastestLapSpeed().isEmpty()) {
                            float speed = (float) Speed.convertMphToKmh(Float.parseFloat(entry.getFastestLapSpeed()));
                            if (speed < minSpeed[0]) {
                                minSpeed[0] = speed;
                            }
                            if (speed > maxSpeed[0]) {
                                maxSpeed[0] = speed;
                            }
                            avgSpeed[0] += speed;
                            count[0]++;
                        }
                    }
                });
            });
            avgSpeed[0] /= count[0];
            drivers.put(driver, new CandleStick(0, avgSpeed[0] - 0.3f, avgSpeed[0] + 0.3f, maxSpeed[0], minSpeed[0]));
        });

        final Map<DriverStanding, CandleStick> result = new LinkedHashMap<>();
        drivers.entrySet().stream()
                .sorted((a, b) -> {
                    float highA = a.getValue() != null ? a.getValue().high : 0;
                    float highB = b.getValue() != null ? b.getValue().high : 0;
                    return Float.compare(highA, highB);
                })
                .forEach(entry -> result.put(entry.getKey(), entry.getValue()));
        return result;
    }
}
