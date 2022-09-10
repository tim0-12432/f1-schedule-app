package de.tim0_12432.f1_schedule_app.data.entity.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Qualifying;
import de.tim0_12432.f1_schedule_app.data.entity.QualifyingResult;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultList;
import de.tim0_12432.f1_schedule_app.data.entity.builder.QualifyingBuilder;
import de.tim0_12432.f1_schedule_app.data.entity.builder.RaceResultListBuilder;

public class QualifyingParser extends AbstractEntityParser<Qualifying> {
    private static final CircuitParser circuitParser = new CircuitParser();
    private static final QualifyingResultParser QUALIFYING_RESULT_PARSER = new QualifyingResultParser();

    @Override
    public Qualifying parse(XmlPullParser parser) throws XmlPullParserException, IOException {
        QualifyingBuilder builder = new QualifyingBuilder()
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
                        builder.withCircuit(circuitParser.parse(parser));
                        break;
                    case "QualifyingList":
                        builder.withResults(parseResults(parser));
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }

    private List<QualifyingResult> parseResults(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<QualifyingResult> list = new ArrayList<>();
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"QualifyingList".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                if ("QualifyingResult".equals(attr)) {
                    list.add(QUALIFYING_RESULT_PARSER.parse(parser));
                }
            }
            parser.nextTag();
        }
        return list;
    }
}