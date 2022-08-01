package de.tim0_12432.f1_schedule_app.data.entity.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.Date;

import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.builder.DriverBuilder;

public class DriverParser {
    public static Driver parseDriver(XmlPullParser parser) throws XmlPullParserException, IOException {
        DriverBuilder builder = new DriverBuilder()
                .withCode(parser.getAttributeValue(null, "code"))
                .withUrl(parser.getAttributeValue(null, "url"));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"Driver".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                switch (attr) {
                    case "PermanentNumber":
                        builder.withNumber(Integer.parseInt(parser.nextText()));
                        break;
                    case "GivenName":
                        builder.withName(parser.nextText());
                        break;
                    case "FamilyName":
                        builder.withFamilyName(parser.nextText());
                        break;
                    case "DateOfBirth":
                        builder.withDateOfBirth(Date.valueOf(parser.nextText()));
                        break;
                    case "Nationality":
                        builder.withNationality(parser.nextText());
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }
}
