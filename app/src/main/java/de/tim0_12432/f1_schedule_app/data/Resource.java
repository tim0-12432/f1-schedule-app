package de.tim0_12432.f1_schedule_app.data;

import de.tim0_12432.f1_schedule_app.data.parser.CircuitsParser;
import de.tim0_12432.f1_schedule_app.data.parser.RaceResultsParser;
import de.tim0_12432.f1_schedule_app.data.parser.ScheduleParser;
import de.tim0_12432.f1_schedule_app.data.parser.DriverRankingParser;
import de.tim0_12432.f1_schedule_app.data.parser.TeamRankingParser;

public enum ResourceNames {
    SCHEDULE(ScheduleParser.class, 72),
    RACE_RESULTS(RaceResultsParser.class, 48),
    DRIVER_RANKING(DriverRankingParser.class, 48),
    TEAM_RANKING(TeamRankingParser.class, 48),
    CIRCUITS(CircuitsParser.class, 24 * 7);

    private final Class parser;

    private final int updateIntervalHours;

    ResourceNames(Class parser, int updateIntervalHours) {
        this.parser = parser;
        this.updateIntervalHours = updateIntervalHours;
    }

    public Class getParser() {
        return parser;
    }

    public int getUpdateIntervalHours() {
        return updateIntervalHours;
    }
}
