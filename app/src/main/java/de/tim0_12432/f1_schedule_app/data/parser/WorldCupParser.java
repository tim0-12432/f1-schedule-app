package de.tim0_12432.f1_schedule_app.data.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;

public class WorldCupParser extends DataSourceParser<DriverStanding> {
    @Override
    protected List<DriverStanding> parse(InputStream input, XmlPullParser parser) throws XmlPullParserException, IOException {
        return Collections.emptyList();
    }
}
