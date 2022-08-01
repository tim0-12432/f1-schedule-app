package de.tim0_12432.f1_schedule_app.data.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Constructor;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.Location;
import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResult;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultList;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultStatus;
import de.tim0_12432.f1_schedule_app.data.entity.builder.CircuitBuilder;
import de.tim0_12432.f1_schedule_app.data.entity.builder.ConstructorBuilder;
import de.tim0_12432.f1_schedule_app.data.entity.builder.DriverBuilder;
import de.tim0_12432.f1_schedule_app.data.entity.builder.LocationBuilder;
import de.tim0_12432.f1_schedule_app.data.entity.builder.RaceBuilder;
import de.tim0_12432.f1_schedule_app.data.entity.builder.RaceResultBuilder;
import de.tim0_12432.f1_schedule_app.data.entity.builder.RaceResultListBuilder;

public class RaceResultsParser extends DataSourceParser<Race> {

    private static final String ITEM_TAG = "Race";

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    protected List<Race> parse(InputStream input, XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Race> result = new ArrayList<>();

        parser.setInput(input, null);
        parser.next();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG) {
                String name = parser.getName();
                if (ITEM_TAG.equals(name)) {
                    Race race = parseRace(parser);
                    result.add(race);
                }
            }
            event = parser.next();
        }
        return result;
    }

    private Race parseRace(XmlPullParser parser) throws XmlPullParserException, IOException {
        RaceBuilder builder = new RaceBuilder()
                .withSeason(Integer.parseInt(parser.getAttributeValue(null, "season")))
                .withRound(Integer.parseInt(parser.getAttributeValue(null, "round")))
                .withUrl(parser.getAttributeValue(null, "url"));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !ITEM_TAG.equals(parser.getName())) {
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
                        builder.withCircuit(parseCircuit(parser));
                        break;
                    case "ResultsList":
                        builder.withResults(parseResults(parser));
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }

    private Circuit parseCircuit(XmlPullParser parser) throws XmlPullParserException, IOException {
        CircuitBuilder builder = new CircuitBuilder()
                .withUrl(parser.getAttributeValue(null, "url"));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"Circuit".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                switch (attr) {
                    case "CircuitName":
                        builder.withName(parser.nextText());
                        break;
                    case "Location":
                        builder.withLocation(parseLocation(parser));
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }

    private Location parseLocation(XmlPullParser parser) throws XmlPullParserException, IOException {
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

    private RaceResultList parseResults(XmlPullParser parser) throws XmlPullParserException, IOException {
        RaceResultListBuilder builder = new RaceResultListBuilder();
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"ResultsList".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                if ("Result".equals(attr)) {
                    builder.withRaceResult(parseResult(parser));
                }
            }
            parser.nextTag();
        }
        return builder.build();
    }

    private RaceResult parseResult(XmlPullParser parser) throws XmlPullParserException, IOException {
        RaceResultBuilder builder = new RaceResultBuilder()
                .withNumber(Integer.parseInt(parser.getAttributeValue(null, "number")))
                .withPosition(Integer.parseInt(parser.getAttributeValue(null, "position")))
                .withPoints(Integer.parseInt(parser.getAttributeValue(null, "points")));
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"Result".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                switch (attr) {
                    case "Driver":
                        builder.withDriver(parseDriver(parser));
                        break;
                    case "Constructor":
                        builder.withTeam(parseTeam(parser));
                        break;
                    case "Grid":
                        builder.withGrid(Integer.parseInt(parser.nextText()));
                        break;
                    case "Laps":
                        builder.withLaps(Integer.parseInt(parser.nextText()));
                        break;
                    case "Status":
                        builder.withStatus(RaceResultStatus.getByCode(
                                Integer.parseInt(parser.getAttributeValue(null, "statusId"))
                        ));
                        parser.nextText();
                        break;
                    case "Time":
                        builder.withRaceTime(parser.nextText());
                        break;
                    case "FastestLap":
                        parseFastestLap(parser, builder);
                        break;
                }
            }
            event = parser.nextTag();
        }
        return builder.build();
    }

    private Driver parseDriver(XmlPullParser parser) throws XmlPullParserException, IOException {
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

    private Constructor parseTeam(XmlPullParser parser) throws XmlPullParserException, IOException {
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

    private void parseFastestLap(XmlPullParser parser, RaceResultBuilder builder) throws XmlPullParserException, IOException {
        parser.nextTag();
        int event = parser.getEventType();
        while (event != XmlPullParser.END_TAG && !"FastestLap".equals(parser.getName())) {
            if (event == XmlPullParser.START_TAG) {
                String attr = parser.getName();
                switch (attr) {
                    case "Time":
                        builder.withFastestLapTime(parser.nextText());
                        break;
                    case "AverageSpeed":
                        builder.withFastestLapSpeed(parser.nextText());
                        break;
                }
            }
            event = parser.nextTag();
        }
    }
}
