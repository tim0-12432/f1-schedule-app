package de.tim0_12432.f1_schedule_app.data.parser;

import org.apache.commons.io.IOUtils;
import org.simpleframework.xml.Serializer;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.RaceTable;

public class ScheduleParser extends DataSourceParser<Race> {
    @Override
    protected List<Race> parse(InputStream input, Serializer serializer) throws Exception {
        System.out.println(IOUtils.toString(input, StandardCharsets.UTF_8));
        RaceTable table = serializer.read(RaceTable.class, input);
        return table.getList();
    }
}
