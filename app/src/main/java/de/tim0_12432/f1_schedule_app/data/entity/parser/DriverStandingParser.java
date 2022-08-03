package de.tim0_12432.f1_schedule_app.data.entity.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;
import de.tim0_12432.f1_schedule_app.data.entity.builder.DriverStandingBuilder;

public class DriverStandingParser {
    public static DriverStanding parseDriverStanding(XmlPullParser parser) throws XmlPullParserException, IOException {
        DriverStandingBuilder builder = new DriverStandingBuilder()
                .withPosition(Integer.parseInt(parser.getAttributeValue(null, "position")))
                .withPoints(Integer.parseInt(parser.getAttributeValue(null, "points")))
                .withWins(Integer.parseInt(parser.getAttributeValue(null, "wins")));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"DriverStanding".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                switch (attr) {
                    case "Driver":
                        builder.withDriver(DriverParser.parseDriver(parser));
                        break;
                    case "Constructor":
                        builder.withConstructor(ConstructorParser.parseConstructor(parser));
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }
}
