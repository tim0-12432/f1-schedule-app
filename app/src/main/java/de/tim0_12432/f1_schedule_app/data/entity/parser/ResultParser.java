package de.tim0_12432.f1_schedule_app.data.entity.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import de.tim0_12432.f1_schedule_app.data.entity.RaceResult;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultList;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultStatus;
import de.tim0_12432.f1_schedule_app.data.entity.builder.RaceResultBuilder;
import de.tim0_12432.f1_schedule_app.data.entity.builder.RaceResultListBuilder;

public class ResultParser {
    public static RaceResultList parseResults(XmlPullParser parser) throws XmlPullParserException, IOException {
        RaceResultListBuilder builder = new RaceResultListBuilder();
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"ResultsList".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                if ("Result".equals(attr)) {
                    builder.withRaceResult(parseResult(parser));
                }
            }
            parser.nextTag();
        }
        return builder.build();
    }

    private static RaceResult parseResult(XmlPullParser parser) throws XmlPullParserException, IOException {
        RaceResultBuilder builder = new RaceResultBuilder()
                .withNumber(Integer.parseInt(parser.getAttributeValue(null, "number")))
                .withPosition(Integer.parseInt(parser.getAttributeValue(null, "position")))
                .withPoints(Integer.parseInt(parser.getAttributeValue(null, "points")));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"Result".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                switch (attr) {
                    case "Driver":
                        builder.withDriver(DriverParser.parseDriver(parser));
                        break;
                    case "Constructor":
                        builder.withTeam(ConstructorParser.parseConstructor(parser));
                        break;
                    case "Grid":
                        builder.withGrid(Integer.parseInt(parser.nextText()));
                        break;
                    case "Laps":
                        builder.withLaps(Integer.parseInt(parser.nextText()));
                        break;
                    case "Status":
                        builder.withStatus(RaceResultStatus.getByCode(
                                Integer.parseInt(parser.getAttributeValue(null, "statusId"))
                        ));
                        parser.nextText();
                        break;
                    case "Time":
                        builder.withRaceTime(parser.nextText());
                        break;
                    case "FastestLap":
                        parseFastestLap(parser, builder);
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }

    private static void parseFastestLap(XmlPullParser parser, RaceResultBuilder builder) throws XmlPullParserException, IOException {
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"FastestLap".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                switch (attr) {
                    case "Time":
                        builder.withFastestLapTime(parser.nextText());
                        break;
                    case "AverageSpeed":
                        builder.withFastestLapSpeed(parser.nextText());
                        break;
                }
            }
            event = parser.nextTag();
        }
    }
}
