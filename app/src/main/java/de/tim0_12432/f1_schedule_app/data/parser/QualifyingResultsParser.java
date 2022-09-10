package de.tim0_12432.f1_schedule_app.data.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Qualifying;
import de.tim0_12432.f1_schedule_app.data.entity.parser.QualifyingParser;

public class QualifyingResultsParser extends AbstractDataSourceParser<Qualifying> {

    @Override
    public String getUrl() {
        return null;
    }

    private static final QualifyingParser qualifyingParser = new QualifyingParser();

    @Override
    protected List<Qualifying> parse(InputStream input, XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Qualifying> result = new ArrayList<>();

        parser.setInput(input, null);
        parser.next();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG) {
                String name = parser.getName();
                if ("Race".equals(name)) {
                    Qualifying qualifying = qualifyingParser.parse(parser);
                    result.add(qualifying);
                }
            }
            event = parser.next();
        }
        return result;
    }
}
