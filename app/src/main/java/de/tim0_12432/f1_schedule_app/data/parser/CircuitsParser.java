package de.tim0_12432.f1_schedule_app.data.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.parser.CircuitParser;

public class CircuitsParser extends AbstractDataSourceParser<Circuit> {
    @Override
    public String getUrl() {
        return "circuits?limit=100";
    }

    private static final CircuitParser circuitParser = new CircuitParser();

    @Override
    protected List<Circuit> parse(InputStream input, XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Circuit> result = new ArrayList<>();

        parser.setInput(input, null);
        parser.next();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG) {
                String name = parser.getName();
                if ("Circuit".equals(name)) {
                    Circuit circuit = circuitParser.parse(parser);
                    result.add(circuit);
                }
            }
            event = parser.next();
        }
        return result;
    }
}
