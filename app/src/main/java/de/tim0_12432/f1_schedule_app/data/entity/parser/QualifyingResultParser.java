package de.tim0_12432.f1_schedule_app.data.entity.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import de.tim0_12432.f1_schedule_app.data.entity.QualifyingResult;
import de.tim0_12432.f1_schedule_app.data.entity.builder.QualifyingResultBuilder;

public class QualifyingResultParser extends AbstractEntityParser<QualifyingResult> {
    private static final DriverParser driverParser = new DriverParser();
    private static final ConstructorParser constructorParser = new ConstructorParser();

    @Override
    public QualifyingResult parse(XmlPullParser parser) throws XmlPullParserException, IOException {
        QualifyingResultBuilder builder = new QualifyingResultBuilder()
                .withNumber(Integer.parseInt(parser.getAttributeValue(null, "number")))
                .withPosition(Integer.parseInt(parser.getAttributeValue(null, "position")));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"QualifyingResult".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                switch (attr) {
                    case "Driver":
                        builder.withDriver(driverParser.parse(parser));
                        break;
                    case "Constructor":
                        builder.withTeam(constructorParser.parse(parser));
                        break;
                    case "Q1":
                        builder.withQ1(parser.nextText());
                        break;
                    case "Q2":
                        builder.withQ2(parser.nextText());
                        break;
                    case "Q3":
                        builder.withQ3(parser.nextText());
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }
}
