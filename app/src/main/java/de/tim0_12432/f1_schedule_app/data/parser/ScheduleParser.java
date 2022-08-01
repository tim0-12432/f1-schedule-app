package de.tim0_12432.f1_schedule_app.data.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Location;
import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.builder.CircuitBuilder;
import de.tim0_12432.f1_schedule_app.data.entity.builder.LocationBuilder;
import de.tim0_12432.f1_schedule_app.data.entity.builder.RaceBuilder;

public class ScheduleParser extends DataSourceParser<Race> {

    private static final String ITEM_TAG = "Race";

    @Override
    public String getUrl() {
        return "current";
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
                    Race race = parseRace(parser);
                    result.add(race);
                }
            }
            event = parser.next();
        }
        return result;
    }

    private Race parseRace(XmlPullParser parser) throws XmlPullParserException, IOException {
        RaceBuilder builder = new RaceBuilder()
                .withSeason(Integer.parseInt(parser.getAttributeValue(null, "season")))
                .withRound(Integer.parseInt(parser.getAttributeValue(null, "round")))
                .withUrl(parser.getAttributeValue(null, "url"));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !ITEM_TAG.equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                switch (attr) {
                    case "RaceName":
                        builder.withName(parser.nextText());
                        break;
                    case "Date":
                        builder.withDate(Date.valueOf(parser.nextText()));
                        break;
                    case "Time":
                        builder.withTime(Time.valueOf(parser.nextText().replace("Z", "")));
                        break;
                    case "Circuit":
                        builder.withCircuit(parseCircuit(parser));
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }

    private Circuit parseCircuit(XmlPullParser parser) throws XmlPullParserException, IOException {
        CircuitBuilder builder = new CircuitBuilder()
                .withUrl(parser.getAttributeValue(null, "url"));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"Circuit".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                switch (attr) {
                    case "CircuitName":
                        builder.withName(parser.nextText());
                        break;
                    case "Location":
                        builder.withLocation(parseLocation(parser));
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }

    private Location parseLocation(XmlPullParser parser) throws XmlPullParserException, IOException {
        LocationBuilder builder = new LocationBuilder()
                .withLat(Double.parseDouble(parser.getAttributeValue(null, "lat")))
                .withLong(Double.parseDouble(parser.getAttributeValue(null, "long")));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"Location".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                switch (attr) {
                    case "Locality":
                        builder.withLocality(parser.nextText());
                        break;
                    case "Country":
                        builder.withCountry(parser.nextText());
                        break;
                }
            }
            parser.nextTag();
        }
        return builder.build();
    }
}
