package de.tim0_12432.f1_schedule_app.data.parser;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Date;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.ConstructorStanding;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;
import de.tim0_12432.f1_schedule_app.data.entity.Race;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class ScheduleParserTest extends AbstractParserTest {

    private ScheduleParser parser;

    @Before
    public void setUp() {
        super.setUp();
        parser = new ScheduleParser();
    }

    @Test
    public void testParse() throws Exception {
        List<Race> list = parser.convert(stream);
        Assert.assertEquals(5, list.size());
        Race race = list.get(2);
        Assert.assertEquals("Chinese Grand Prix", race.getName());
        Assert.assertEquals(Date.valueOf("2019-04-16"), race.getDate());
        Assert.assertEquals("Shanghai", race.getCircuit().getLocation().getLocality());
        Assert.assertEquals("Shanghai International Circuit", race.getCircuit().getName());
    }

    @Test
    public void testParseError() throws Exception {
        List<Race> list = parser.convert(AbstractParserTest.ERROR_STREAM);
        Assert.assertEquals(0, list.size());
    }

    @After
    public void tearDown() {
        super.tearDown();
        parser = null;
    }

    @Override
    protected String getXmlString() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"/schemas/mrd-1.4.xsl\"?>\n" +
                "<MRData xmlns=\"http://ergast.com/mrd/1.4\" series=\"f1\" url=\"http://ergast.com/api/f1/2019\" limit=\"30\" offset=\"0\" total=\"21\">\n" +
                "    <RaceTable season=\"2019\">\n" +
                "        <Race season=\"2019\" round=\"1\" url=\"https://en.wikipedia.org/wiki/2019_Australian_Grand_Prix\">\n" +
                "            <RaceName>Australian Grand Prix</RaceName>\n" +
                "            <Circuit circuitId=\"albert_park\" url=\"http://en.wikipedia.org/wiki/Melbourne_Grand_Prix_Circuit\">\n" +
                "                <CircuitName>Albert Park Grand Prix Circuit</CircuitName>\n" +
                "                <Location lat=\"-37.8497\" long=\"144.968\">\n" +
                "                    <Locality>Melbourne</Locality>\n" +
                "                    <Country>Australia</Country>\n" +
                "                </Location>\n" +
                "            </Circuit>\n" +
                "            <Date>2019-03-17</Date>\n" +
                "            <Time>05:10:00Z</Time>\n" +
                "        </Race>\n" +
                "        <Race season=\"2019\" round=\"2\" url=\"https://en.wikipedia.org/wiki/2019_Bahrain_Grand_Prix\">\n" +
                "            <RaceName>Bahrain Grand Prix</RaceName>\n" +
                "            <Circuit circuitId=\"bahrain\" url=\"http://en.wikipedia.org/wiki/Bahrain_International_Circuit\">\n" +
                "                <CircuitName>Bahrain International Circuit</CircuitName>\n" +
                "                <Location lat=\"26.0325\" long=\"50.5106\">\n" +
                "                    <Locality>Sakhir</Locality>\n" +
                "                    <Country>Bahrain</Country>\n" +
                "                </Location>\n" +
                "            </Circuit>\n" +
                "            <Date>2019-03-31</Date>\n" +
                "            <Time>15:10:00Z</Time>\n" +
                "        </Race>\n" +
                "        <Race season=\"2019\" round=\"3\" url=\"https://en.wikipedia.org/wiki/2019_Chinese_Grand_Prix\">\n" +
                "            <RaceName>Chinese Grand Prix</RaceName>\n" +
                "            <Circuit circuitId=\"shanghai\" url=\"http://en.wikipedia.org/wiki/Shanghai_International_Circuit\">\n" +
                "                <CircuitName>Shanghai International Circuit</CircuitName>\n" +
                "                <Location lat=\"31.3389\" long=\"121.22\">\n" +
                "                    <Locality>Shanghai</Locality>\n" +
                "                    <Country>China</Country>\n" +
                "                </Location>\n" +
                "            </Circuit>\n" +
                "            <Date>2019-04-14</Date>\n" +
                "            <Time>06:10:00Z</Time>\n" +
                "        </Race>\n" +
                "        <Race season=\"2019\" round=\"4\" url=\"https://en.wikipedia.org/wiki/2019_Azerbaijan_Grand_Prix\">\n" +
                "            <RaceName>Azerbaijan Grand Prix</RaceName>\n" +
                "            <Circuit circuitId=\"BAK\" url=\"http://en.wikipedia.org/wiki/Baku_City_Circuit\">\n" +
                "                <CircuitName>Baku City Circuit</CircuitName>\n" +
                "                <Location lat=\"40.3725\" long=\"49.8533\">\n" +
                "                    <Locality>Baku</Locality>\n" +
                "                    <Country>Azerbaijan</Country>\n" +
                "                </Location>\n" +
                "            </Circuit>\n" +
                "            <Date>2019-04-28</Date>\n" +
                "            <Time>12:10:00Z</Time>\n" +
                "        </Race>\n" +
                "        <Race season=\"2019\" round=\"5\" url=\"https://en.wikipedia.org/wiki/2019_Spanish_Grand_Prix\">\n" +
                "            <RaceName>Spanish Grand Prix</RaceName>\n" +
                "            <Circuit circuitId=\"catalunya\" url=\"http://en.wikipedia.org/wiki/Circuit_de_Barcelona-Catalunya\">\n" +
                "                <CircuitName>Circuit de Barcelona-Catalunya</CircuitName>\n" +
                "                <Location lat=\"41.57\" long=\"2.26111\">\n" +
                "                    <Locality>Montmeló</Locality>\n" +
                "                    <Country>Spain</Country>\n" +
                "                </Location>\n" +
                "            </Circuit>\n" +
                "            <Date>2019-05-12</Date>\n" +
                "            <Time>13:10:00Z</Time>\n" +
                "        </Race>" +
                "    </RaceTable>\n" +
                "</MRData>";
    }
}
