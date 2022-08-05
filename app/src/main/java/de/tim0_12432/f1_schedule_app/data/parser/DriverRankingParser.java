package de.tim0_12432.f1_schedule_app.data.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;
import de.tim0_12432.f1_schedule_app.data.entity.parser.DriverStandingParser;

public class DriverRankingParser extends AbstractDataSourceParser<DriverStanding> {
    @Override
    public String getUrl() {
        return "current/driverStandings";
    }

    private static final DriverStandingParser driverStandingParser = new DriverStandingParser();

    @Override
    protected List<DriverStanding> parse(InputStream input, XmlPullParser parser) throws XmlPullParserException, IOException {
        List<DriverStanding> result = new ArrayList<>();

        parser.setInput(input, null);
        parser.next();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG) {
                String name = parser.getName();
                if ("DriverStanding".equals(name)) {
                    DriverStanding standing = driverStandingParser.parse(parser);
                    result.add(standing);
                }
            }
            event = parser.next();
        }
        return result;
    }
}
