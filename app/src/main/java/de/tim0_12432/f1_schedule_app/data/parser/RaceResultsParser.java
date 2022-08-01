package de.tim0_12432.f1_schedule_app.data.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.parser.RaceParser;

public class RaceResultsParser extends DataSourceParser<Race> {

    private static final String ITEM_TAG = "Race";

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    protected List<Race> parse(InputStream input, XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Race> result = new ArrayList<>();

        parser.setInput(input, null);
        parser.next();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG) {
                String name = parser.getName();
                if (ITEM_TAG.equals(name)) {
                    Race race = RaceParser.parseRaceWithResults(parser);
                    result.add(race);
                }
            }
            event = parser.next();
        }
        return result;
    }
}