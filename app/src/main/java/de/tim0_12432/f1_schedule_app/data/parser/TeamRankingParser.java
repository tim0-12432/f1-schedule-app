package de.tim0_12432.f1_schedule_app.data.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.ConstructorStanding;
import de.tim0_12432.f1_schedule_app.data.entity.parser.ConstructorStandingParser;

public class TeamRankingParser extends DataSourceParser<ConstructorStanding> {
    @Override
    public String getUrl() {
        return "current/constructorStandings";
    }

    @Override
    protected List<ConstructorStanding> parse(InputStream input, XmlPullParser parser) throws XmlPullParserException, IOException {
        List<ConstructorStanding> result = new ArrayList<>();

        parser.setInput(input, null);
        parser.next();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG) {
                String name = parser.getName();
                if ("ConstructorStanding".equals(name)) {
                    ConstructorStanding standing = ConstructorStandingParser.parseConstructorStanding(parser);
                    result.add(standing);
                }
            }
            event = parser.next();
        }
        return result;
    }
}
