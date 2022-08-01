package de.tim0_12432.f1_schedule_app.data;

import de.tim0_12432.f1_schedule_app.data.parser.RaceResultsParser;
import de.tim0_12432.f1_schedule_app.data.parser.ScheduleParser;
import de.tim0_12432.f1_schedule_app.data.parser.WorldCupParser;

public enum ResourceNames {
    SCHEDULE(ScheduleParser.class),
    RACE_RESULTS(RaceResultsParser.class),
    WORLD_CUP(WorldCupParser.class),
    DRIVERS(null),
    CIRCUITS(null);

    private final String url;
    private final Class parser;

    ResourceNames(Class parser) {
        this(parser, null);
    }

    ResourceNames(Class parser, String url) {
        this.url = url;
        this.parser = parser;
    }

    public String getUrl() {
        return url;
    }

    public Class getParser() {
        return parser;
    }
}
