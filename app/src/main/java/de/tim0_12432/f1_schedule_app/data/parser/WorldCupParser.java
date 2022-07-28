package de.tim0_12432.f1_schedule_app.data.parser;

import org.simpleframework.xml.Serializer;

import java.io.InputStream;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;
import de.tim0_12432.f1_schedule_app.data.entity.DriverStandings;

public class WorldCupParser extends DataSourceParser<DriverStanding> {
    @Override
    protected List<DriverStanding> parse(InputStream input, Serializer serializer) throws Exception {
        DriverStandings table = serializer.read(DriverStandings.class, input);
        return table.getTable().getList();
    }
}
