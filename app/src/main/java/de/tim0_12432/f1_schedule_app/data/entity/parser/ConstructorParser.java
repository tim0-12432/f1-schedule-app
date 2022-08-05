package de.tim0_12432.f1_schedule_app.data.entity.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import de.tim0_12432.f1_schedule_app.data.entity.Constructor;
import de.tim0_12432.f1_schedule_app.data.entity.builder.ConstructorBuilder;

public class ConstructorParser extends AbstractEntityParser<Constructor> {
    @Override
    public Constructor parse(XmlPullParser parser) throws XmlPullParserException, IOException {
        ConstructorBuilder builder = new ConstructorBuilder()
                .withUrl(parser.getAttributeValue(null, "url"));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"Constructor".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                switch (attr) {
                    case "Name":
                        builder.withName(parser.nextText());
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
