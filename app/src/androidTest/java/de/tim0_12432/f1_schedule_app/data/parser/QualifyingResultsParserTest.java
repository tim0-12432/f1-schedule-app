package de.tim0_12432.f1_schedule_app.data.parser;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Qualifying;
import de.tim0_12432.f1_schedule_app.data.entity.QualifyingResult;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class QualifyingResultsParserTest extends AbstractParserTest {

    private QualifyingResultsParser parser;

    @Before
    public void setUp() {
        super.setUp();
        parser = new QualifyingResultsParser();
    }

    @Test
    public void testParse() throws Exception {
        List<Qualifying> list = parser.convert(stream);
        Assert.assertEquals(1, list.size());
        Qualifying race = list.get(0);
        List<QualifyingResult> results = race.getResults();
        Assert.assertEquals(5, results.size());
        Assert.assertEquals(1, results.get(0).getPosition());
        Assert.assertEquals("Red Bull", results.get(4).getTeam().getName());
        Assert.assertEquals("Melbourne", race.getCircuit().getLocation().getLocality());
        Assert.assertEquals("1:20.598", results.get(1).getLapTimes()[2]);
        Assert.assertEquals("1:23.017", results.get(3).getLapTimes()[0]);
        Assert.assertNull(results.get(3).getLapTimes()[2]);
    }

    @Test
    public void testParseError() throws Exception {
        List<Qualifying> list = parser.convert(AbstractParserTest.ERROR_STREAM);
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
                "<MRData xmlns=\"http://ergast.com/mrd/1.4\" series=\"f1\" url=\"http://ergast.com/api/f1/2019/1/qualifying\" limit=\"30\" offset=\"0\" total=\"20\">\n" +
                "    <RaceTable season=\"2019\" round=\"1\">\n" +
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
                "            <QualifyingList>\n" +
                "                <QualifyingResult number=\"44\" position=\"1\">\n" +
                "                    <Driver driverId=\"hamilton\" code=\"HAM\" url=\"http://en.wikipedia.org/wiki/Lewis_Hamilton\">\n" +
                "                        <PermanentNumber>44</PermanentNumber>\n" +
                "                        <GivenName>Lewis</GivenName>\n" +
                "                        <FamilyName>Hamilton</FamilyName>\n" +
                "                        <DateOfBirth>1985-01-07</DateOfBirth>\n" +
                "                        <Nationality>British</Nationality>\n" +
                "                    </Driver>\n" +
                "                    <Constructor constructorId=\"mercedes\" url=\"http://en.wikipedia.org/wiki/Mercedes-Benz_in_Formula_One\">\n" +
                "                        <Name>Mercedes</Name>\n" +
                "                        <Nationality>German</Nationality>\n" +
                "                    </Constructor>\n" +
                "                    <Q1>1:22.043</Q1>\n" +
                "                    <Q2>1:21.014</Q2>\n" +
                "                    <Q3>1:20.486</Q3>\n" +
                "                </QualifyingResult>\n" +
                "                <QualifyingResult number=\"77\" position=\"2\">\n" +
                "                    <Driver driverId=\"bottas\" code=\"BOT\" url=\"http://en.wikipedia.org/wiki/Valtteri_Bottas\">\n" +
                "                        <PermanentNumber>77</PermanentNumber>\n" +
                "                        <GivenName>Valtteri</GivenName>\n" +
                "                        <FamilyName>Bottas</FamilyName>\n" +
                "                        <DateOfBirth>1989-08-28</DateOfBirth>\n" +
                "                        <Nationality>Finnish</Nationality>\n" +
                "                    </Driver>\n" +
                "                    <Constructor constructorId=\"mercedes\" url=\"http://en.wikipedia.org/wiki/Mercedes-Benz_in_Formula_One\">\n" +
                "                        <Name>Mercedes</Name>\n" +
                "                        <Nationality>German</Nationality>\n" +
                "                    </Constructor>\n" +
                "                    <Q1>1:22.367</Q1>\n" +
                "                    <Q2>1:21.193</Q2>\n" +
                "                    <Q3>1:20.598</Q3>\n" +
                "                </QualifyingResult>\n" +
                "                <QualifyingResult number=\"27\" position=\"11\">\n" +
                "                    <Driver driverId=\"hulkenberg\" code=\"HUL\" url=\"http://en.wikipedia.org/wiki/Nico_H%C3%BClkenberg\">\n" +
                "                        <PermanentNumber>27</PermanentNumber>\n" +
                "                        <GivenName>Nico</GivenName>\n" +
                "                        <FamilyName>Hülkenberg</FamilyName>\n" +
                "                        <DateOfBirth>1987-08-19</DateOfBirth>\n" +
                "                        <Nationality>German</Nationality>\n" +
                "                    </Driver>\n" +
                "                    <Constructor constructorId=\"renault\" url=\"http://en.wikipedia.org/wiki/Renault_in_Formula_One\">\n" +
                "                        <Name>Renault</Name>\n" +
                "                        <Nationality>French</Nationality>\n" +
                "                    </Constructor>\n" +
                "                    <Q1>1:22.540</Q1>\n" +
                "                    <Q2>1:22.562</Q2>\n" +
                "                </QualifyingResult>\n" +
                "                <QualifyingResult number=\"18\" position=\"16\">\n" +
                "                    <Driver driverId=\"stroll\" code=\"STR\" url=\"http://en.wikipedia.org/wiki/Lance_Stroll\">\n" +
                "                        <PermanentNumber>18</PermanentNumber>\n" +
                "                        <GivenName>Lance</GivenName>\n" +
                "                        <FamilyName>Stroll</FamilyName>\n" +
                "                        <DateOfBirth>1998-10-29</DateOfBirth>\n" +
                "                        <Nationality>Canadian</Nationality>\n" +
                "                    </Driver>\n" +
                "                    <Constructor constructorId=\"racing_point\" url=\"http://en.wikipedia.org/wiki/Racing_Point_F1_Team\">\n" +
                "                        <Name>Racing Point</Name>\n" +
                "                        <Nationality>British</Nationality>\n" +
                "                    </Constructor>\n" +
                "                    <Q1>1:23.017</Q1>\n" +
                "                </QualifyingResult>\n" +
                "                <QualifyingResult number=\"10\" position=\"17\">\n" +
                "                    <Driver driverId=\"gasly\" code=\"GAS\" url=\"http://en.wikipedia.org/wiki/Pierre_Gasly\">\n" +
                "                        <PermanentNumber>10</PermanentNumber>\n" +
                "                        <GivenName>Pierre</GivenName>\n" +
                "                        <FamilyName>Gasly</FamilyName>\n" +
                "                        <DateOfBirth>1996-02-07</DateOfBirth>\n" +
                "                        <Nationality>French</Nationality>\n" +
                "                    </Driver>\n" +
                "                    <Constructor constructorId=\"red_bull\" url=\"http://en.wikipedia.org/wiki/Red_Bull_Racing\">\n" +
                "                        <Name>Red Bull</Name>\n" +
                "                        <Nationality>Austrian</Nationality>\n" +
                "                    </Constructor>\n" +
                "                    <Q1>1:23.020</Q1>\n" +
                "                </QualifyingResult>\n" +
                "            </QualifyingList>\n" +
                "        </Race>\n" +
                "    </RaceTable>\n" +
                "</MRData>";
    }
}
