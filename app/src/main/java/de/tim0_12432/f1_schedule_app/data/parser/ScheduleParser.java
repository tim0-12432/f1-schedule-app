package de.tim0_12432.f1_schedule_app.data.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.parser.RaceParser;

public class ScheduleParser extends AbstractDataSourceParser<Race> {

    @Override
    public String getUrl() {
        return "current";
    }

    private static final RaceParser raceParser = new RaceParser();

    @Override
    protected List<Race> parse(InputStream input, XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Race> result = new ArrayList<>();

        parser.setInput(input, null);
        parser.next();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG) {
                String name = parser.getName();
                if ("Race".equals(name)) {
                    Race race = raceParser.parse(parser);
                    result.add(race);
                }
            }
            event = parser.next();
        }
        return result;
    }
}
