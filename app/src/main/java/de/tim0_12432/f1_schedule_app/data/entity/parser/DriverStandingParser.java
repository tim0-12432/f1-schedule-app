package de.tim0_12432.f1_schedule_app.data.entity.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;
import de.tim0_12432.f1_schedule_app.data.entity.builder.DriverStandingBuilder;

public class DriverStandingParser extends AbstractEntityParser<DriverStanding> {
    private static final DriverParser driverParser = new DriverParser();
    private static final ConstructorParser constructorParser = new ConstructorParser();

    @Override
    public DriverStanding parse(XmlPullParser parser) throws XmlPullParserException, IOException {
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
                        builder.withDriver(driverParser.parse(parser));
                        break;
                    case "Constructor":
                        builder.withConstructor(constructorParser.parse(parser));
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }
}
