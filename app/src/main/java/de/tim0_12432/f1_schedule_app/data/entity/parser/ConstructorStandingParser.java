package de.tim0_12432.f1_schedule_app.data.entity.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import de.tim0_12432.f1_schedule_app.data.entity.ConstructorStanding;
import de.tim0_12432.f1_schedule_app.data.entity.builder.ConstructorStandingBuilder;

public class ConstructorStandingParser {
    public static ConstructorStanding parseConstructorStanding(XmlPullParser parser) throws XmlPullParserException, IOException {
        ConstructorStandingBuilder builder = new ConstructorStandingBuilder()
                .withPosition(Integer.parseInt(parser.getAttributeValue(null, "position")))
                .withPoints(Integer.parseInt(parser.getAttributeValue(null, "points")))
                .withWins(Integer.parseInt(parser.getAttributeValue(null, "wins")));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"ConstructorStanding".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                if ("Constructor".equals(attr)) {
                    builder.withConstructor(ConstructorParser.parseConstructor(parser));
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }
}
