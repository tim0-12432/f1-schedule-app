package de.tim0_12432.f1_schedule_app.data.entity.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.builder.RaceBuilder;

public class RaceParser {
    public static Race parseRace(XmlPullParser parser) throws XmlPullParserException, IOException {
        RaceBuilder builder = new RaceBuilder()
                .withSeason(Integer.parseInt(parser.getAttributeValue(null, "season")))
                .withRound(Integer.parseInt(parser.getAttributeValue(null, "round")))
                .withUrl(parser.getAttributeValue(null, "url"));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"Race".equals(parser.getName())) {
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
                        builder.withCircuit(CircuitParser.parseCircuit(parser));
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }

    public static Race parseRaceWithResults(XmlPullParser parser) throws XmlPullParserException, IOException {
        RaceBuilder builder = new RaceBuilder()
                .withSeason(Integer.parseInt(parser.getAttributeValue(null, "season")))
                .withRound(Integer.parseInt(parser.getAttributeValue(null, "round")))
                .withUrl(parser.getAttributeValue(null, "url"));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"Race".equals(parser.getName())) {
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
                        builder.withCircuit(CircuitParser.parseCircuit(parser));
                        break;
                    case "ResultsList":
                        builder.withResults(ResultParser.parseResults(parser));
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }
}
