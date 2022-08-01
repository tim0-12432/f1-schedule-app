package de.tim0_12432.f1_schedule_app.data.entity.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import de.tim0_12432.f1_schedule_app.data.entity.Location;
import de.tim0_12432.f1_schedule_app.data.entity.builder.LocationBuilder;

public class LocationParser {
    public static Location parseLocation(XmlPullParser parser) throws XmlPullParserException, IOException {
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
