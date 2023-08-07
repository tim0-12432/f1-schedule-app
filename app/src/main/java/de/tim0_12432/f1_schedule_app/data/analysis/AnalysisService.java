package de.tim0_12432.f1_schedule_app.data.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.data.DataManager;
import de.tim0_12432.f1_schedule_app.data.Resource;
import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;
import de.tim0_12432.f1_schedule_app.data.entity.Qualifying;
import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.source.ILoadCallback;
import de.tim0_12432.f1_schedule_app.utility.DateTime;

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

    public Map<String, Integer> getPointsPerDriver() {
        return driverStandings.stream()
                .collect(Collectors.toMap(standing -> standing.getDriver().getCode(), DriverStanding::getPoints));
    }
}
