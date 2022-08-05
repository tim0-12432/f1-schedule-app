package de.tim0_12432.f1_schedule_app.data.parser;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResult;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class RaceResultsParserTest extends AbstractParserTest {

    private RaceResultsParser parser;

    @Before
    public void setUp() {
        super.setUp();
        parser = new RaceResultsParser();
    }

    @Test
    public void testParse() throws Exception {
        List<Race> list = parser.convert(stream);
        Assert.assertEquals(1, list.size());
        Race race = list.get(0);
        List<RaceResult> results = race.getResults().getResults();
        Assert.assertEquals(5, results.size());
        Assert.assertEquals(2, results.get(0).getGrid());
        Assert.assertEquals("Ferrari", results.get(4).getTeam().getName());
        Assert.assertEquals("Melbourne", race.getCircuit().getLocation().getLocality());
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
                "<MRData xmlns=\"http://ergast.com/mrd/1.4\" series=\"f1\" url=\"http://ergast.com/api/f1/2019/1/results\" limit=\"30\" offset=\"0\" total=\"5\">\n" +
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
                "            <ResultsList>\n" +
                "                <Result number=\"77\" position=\"1\" positionText=\"1\" points=\"26\">\n" +
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
                "                    <Grid>2</Grid>\n" +
                "                    <Laps>58</Laps>\n" +
                "                    <Status statusId=\"1\">Finished</Status>\n" +
                "                    <Time millis=\"5127325\">1:25:27.325</Time>\n" +
                "                    <FastestLap rank=\"1\" lap=\"57\">\n" +
                "                        <Time>1:25.580</Time>\n" +
                "                        <AverageSpeed units=\"kph\">223.075</AverageSpeed>\n" +
                "                    </FastestLap>\n" +
                "                </Result>\n" +
                "                <Result number=\"44\" position=\"2\" positionText=\"2\" points=\"18\">\n" +
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
                "                    <Grid>1</Grid>\n" +
                "                    <Laps>58</Laps>\n" +
                "                    <Status statusId=\"1\">Finished</Status>\n" +
                "                    <Time millis=\"5148211\">+20.886</Time>\n" +
                "                    <FastestLap rank=\"2\" lap=\"57\">\n" +
                "                        <Time>1:26.057</Time>\n" +
                "                        <AverageSpeed units=\"kph\">221.839</AverageSpeed>\n" +
                "                    </FastestLap>\n" +
                "                </Result>\n" +
                "                <Result number=\"33\" position=\"3\" positionText=\"3\" points=\"15\">\n" +
                "                    <Driver driverId=\"max_verstappen\" code=\"VER\" url=\"http://en.wikipedia.org/wiki/Max_Verstappen\">\n" +
                "                        <PermanentNumber>33</PermanentNumber>\n" +
                "                        <GivenName>Max</GivenName>\n" +
                "                        <FamilyName>Verstappen</FamilyName>\n" +
                "                        <DateOfBirth>1997-09-30</DateOfBirth>\n" +
                "                        <Nationality>Dutch</Nationality>\n" +
                "                    </Driver>\n" +
                "                    <Constructor constructorId=\"red_bull\" url=\"http://en.wikipedia.org/wiki/Red_Bull_Racing\">\n" +
                "                        <Name>Red Bull</Name>\n" +
                "                        <Nationality>Austrian</Nationality>\n" +
                "                    </Constructor>\n" +
                "                    <Grid>4</Grid>\n" +
                "                    <Laps>58</Laps>\n" +
                "                    <Status statusId=\"1\">Finished</Status>\n" +
                "                    <Time millis=\"5149845\">+22.520</Time>\n" +
                "                    <FastestLap rank=\"3\" lap=\"57\">\n" +
                "                        <Time>1:26.256</Time>\n" +
                "                        <AverageSpeed units=\"kph\">221.327</AverageSpeed>\n" +
                "                    </FastestLap>\n" +
                "                </Result>\n" +
                "                <Result number=\"5\" position=\"4\" positionText=\"4\" points=\"12\">\n" +
                "                    <Driver driverId=\"vettel\" code=\"VET\" url=\"http://en.wikipedia.org/wiki/Sebastian_Vettel\">\n" +
                "                        <PermanentNumber>5</PermanentNumber>\n" +
                "                        <GivenName>Sebastian</GivenName>\n" +
                "                        <FamilyName>Vettel</FamilyName>\n" +
                "                        <DateOfBirth>1987-07-03</DateOfBirth>\n" +
                "                        <Nationality>German</Nationality>\n" +
                "                    </Driver>\n" +
                "                    <Constructor constructorId=\"ferrari\" url=\"http://en.wikipedia.org/wiki/Scuderia_Ferrari\">\n" +
                "                        <Name>Ferrari</Name>\n" +
                "                        <Nationality>Italian</Nationality>\n" +
                "                    </Constructor>\n" +
                "                    <Grid>3</Grid>\n" +
                "                    <Laps>58</Laps>\n" +
                "                    <Status statusId=\"1\">Finished</Status>\n" +
                "                    <Time millis=\"5184434\">+57.109</Time>\n" +
                "                    <FastestLap rank=\"8\" lap=\"16\">\n" +
                "                        <Time>1:27.954</Time>\n" +
                "                        <AverageSpeed units=\"kph\">217.054</AverageSpeed>\n" +
                "                    </FastestLap>\n" +
                "                </Result>\n" +
                "                <Result number=\"16\" position=\"5\" positionText=\"5\" points=\"10\">\n" +
                "                    <Driver driverId=\"leclerc\" code=\"LEC\" url=\"http://en.wikipedia.org/wiki/Charles_Leclerc\">\n" +
                "                        <PermanentNumber>16</PermanentNumber>\n" +
                "                        <GivenName>Charles</GivenName>\n" +
                "                        <FamilyName>Leclerc</FamilyName>\n" +
                "                        <DateOfBirth>1997-10-16</DateOfBirth>\n" +
                "                        <Nationality>Monegasque</Nationality>\n" +
                "                    </Driver>\n" +
                "                    <Constructor constructorId=\"ferrari\" url=\"http://en.wikipedia.org/wiki/Scuderia_Ferrari\">\n" +
                "                        <Name>Ferrari</Name>\n" +
                "                        <Nationality>Italian</Nationality>\n" +
                "                    </Constructor>\n" +
                "                    <Grid>5</Grid>\n" +
                "                    <Laps>58</Laps>\n" +
                "                    <Status statusId=\"1\">Finished</Status>\n" +
                "                    <Time millis=\"5185528\">+58.203</Time>\n" +
                "                    <FastestLap rank=\"4\" lap=\"58\">\n" +
                "                        <Time>1:26.926</Time>\n" +
                "                        <AverageSpeed units=\"kph\">219.621</AverageSpeed>\n" +
                "                    </FastestLap>\n" +
                "                </Result>\n" +
                "            </ResultsList>\n" +
                "        </Race>\n" +
                "    </RaceTable>\n" +
                "</MRData>";
    }
}
