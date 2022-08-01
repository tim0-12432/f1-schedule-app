package de.tim0_12432.f1_schedule_app.data.entity.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.builder.CircuitBuilder;

public class CircuitParser {
    public static Circuit parseCircuit(XmlPullParser parser) throws XmlPullParserException, IOException {
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
                        builder.withLocation(LocationParser.parseLocation(parser));
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }
}
