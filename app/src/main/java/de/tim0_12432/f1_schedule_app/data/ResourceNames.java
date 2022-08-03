package de.tim0_12432.f1_schedule_app.data;

import de.tim0_12432.f1_schedule_app.data.parser.RaceResultsParser;
import de.tim0_12432.f1_schedule_app.data.parser.ScheduleParser;
import de.tim0_12432.f1_schedule_app.data.parser.DriverRankingParser;
import de.tim0_12432.f1_schedule_app.data.parser.TeamRankingParser;

public enum ResourceNames {
    SCHEDULE(ScheduleParser.class),
    RACE_RESULTS(RaceResultsParser.class),
    DRIVER_RANKING(DriverRankingParser.class),
    TEAM_RANKING(TeamRankingParser.class),
    CIRCUITS(null);

    private final Class parser;

    ResourceNames(Class parser) {
        this.parser = parser;
    }

    public Class getParser() {
        return parser;
    }
}
