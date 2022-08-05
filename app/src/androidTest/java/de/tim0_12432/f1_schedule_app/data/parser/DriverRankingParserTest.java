package de.tim0_12432.f1_schedule_app.data.parser;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class DriverRankingParserTest extends AbstractParserTest {

    private DriverRankingParser parser;

    @Before
    public void setUp() {
        super.setUp();
        parser = new DriverRankingParser();
    }

    @Test
    public void testParse() throws Exception {
        List<DriverStanding> list = parser.convert(stream);
        Assert.assertEquals(6, list.size());
        DriverStanding standing = list.get(4);
        Assert.assertEquals("Charles", standing.getDriver().getName());
        Assert.assertEquals(10, standing.getPoints());
        Assert.assertEquals(0, standing.getWins());
        Assert.assertEquals("Ferrari", standing.getConstructor().getName());
        Assert.assertEquals(Nationality.MONEGASQUE, standing.getDriver().getNationality());
    }

    @Test
    public void testParseError() throws Exception {
        List<DriverStanding> list = parser.convert(AbstractParserTest.ERROR_STREAM);
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
                "<MRData xmlns=\"http://ergast.com/mrd/1.4\" series=\"f1\" url=\"http://ergast.com/api/f1/current/driverStandings\" limit=\"30\" offset=\"0\" total=\"6\">\n" +
                "    <StandingsTable season=\"2019\" round=\"1\">\n" +
                "        <StandingsList season=\"2019\" round=\"1\">\n" +
                "            <DriverStanding position=\"1\" positionText=\"1\" points=\"26\" wins=\"1\">\n" +
                "                <Driver driverId=\"bottas\" code=\"BOT\" url=\"http://en.wikipedia.org/wiki/Valtteri_Bottas\">\n" +
                "                    <PermanentNumber>77</PermanentNumber>\n" +
                "                    <GivenName>Valtteri</GivenName>\n" +
                "                    <FamilyName>Bottas</FamilyName>\n" +
                "                    <DateOfBirth>1989-08-28</DateOfBirth>\n" +
                "                    <Nationality>Finnish</Nationality>\n" +
                "                </Driver>\n" +
                "                <Constructor constructorId=\"mercedes\" url=\"http://en.wikipedia.org/wiki/Mercedes-Benz_in_Formula_One\">\n" +
                "                    <Name>Mercedes</Name>\n" +
                "                    <Nationality>German</Nationality>\n" +
                "                </Constructor>\n" +
                "            </DriverStanding>\n" +
                "            <DriverStanding position=\"2\" positionText=\"2\" points=\"18\" wins=\"0\">\n" +
                "                <Driver driverId=\"hamilton\" code=\"HAM\" url=\"http://en.wikipedia.org/wiki/Lewis_Hamilton\">\n" +
                "                    <PermanentNumber>44</PermanentNumber>\n" +
                "                    <GivenName>Lewis</GivenName>\n" +
                "                    <FamilyName>Hamilton</FamilyName>\n" +
                "                    <DateOfBirth>1985-01-07</DateOfBirth>\n" +
                "                    <Nationality>British</Nationality>\n" +
                "                </Driver>\n" +
                "                <Constructor constructorId=\"mercedes\" url=\"http://en.wikipedia.org/wiki/Mercedes-Benz_in_Formula_One\">\n" +
                "                    <Name>Mercedes</Name>\n" +
                "                    <Nationality>German</Nationality>\n" +
                "                </Constructor>\n" +
                "            </DriverStanding>\n" +
                "            <DriverStanding position=\"3\" positionText=\"3\" points=\"15\" wins=\"0\">\n" +
                "                <Driver driverId=\"max_verstappen\" code=\"VER\" url=\"http://en.wikipedia.org/wiki/Max_Verstappen\">\n" +
                "                    <PermanentNumber>33</PermanentNumber>\n" +
                "                    <GivenName>Max</GivenName>\n" +
                "                    <FamilyName>Verstappen</FamilyName>\n" +
                "                    <DateOfBirth>1997-09-30</DateOfBirth>\n" +
                "                    <Nationality>Dutch</Nationality>\n" +
                "                </Driver>\n" +
                "                <Constructor constructorId=\"red_bull\" url=\"http://en.wikipedia.org/wiki/Red_Bull_Racing\">\n" +
                "                    <Name>Red Bull</Name>\n" +
                "                    <Nationality>Austrian</Nationality>\n" +
                "                </Constructor>\n" +
                "            </DriverStanding>\n" +
                "            <DriverStanding position=\"4\" positionText=\"4\" points=\"12\" wins=\"0\">\n" +
                "                <Driver driverId=\"vettel\" code=\"VET\" url=\"http://en.wikipedia.org/wiki/Sebastian_Vettel\">\n" +
                "                    <PermanentNumber>5</PermanentNumber>\n" +
                "                    <GivenName>Sebastian</GivenName>\n" +
                "                    <FamilyName>Vettel</FamilyName>\n" +
                "                    <DateOfBirth>1987-07-03</DateOfBirth>\n" +
                "                    <Nationality>German</Nationality>\n" +
                "                </Driver>\n" +
                "                <Constructor constructorId=\"ferrari\" url=\"http://en.wikipedia.org/wiki/Scuderia_Ferrari\">\n" +
                "                    <Name>Ferrari</Name>\n" +
                "                    <Nationality>Italian</Nationality>\n" +
                "                </Constructor>\n" +
                "            </DriverStanding>\n" +
                "            <DriverStanding position=\"5\" positionText=\"5\" points=\"10\" wins=\"0\">\n" +
                "                <Driver driverId=\"leclerc\" code=\"LEC\" url=\"http://en.wikipedia.org/wiki/Charles_Leclerc\">\n" +
                "                    <PermanentNumber>16</PermanentNumber>\n" +
                "                    <GivenName>Charles</GivenName>\n" +
                "                    <FamilyName>Leclerc</FamilyName>\n" +
                "                    <DateOfBirth>1997-10-16</DateOfBirth>\n" +
                "                    <Nationality>Monegasque</Nationality>\n" +
                "                </Driver>\n" +
                "                <Constructor constructorId=\"ferrari\" url=\"http://en.wikipedia.org/wiki/Scuderia_Ferrari\">\n" +
                "                    <Name>Ferrari</Name>\n" +
                "                    <Nationality>Italian</Nationality>\n" +
                "                </Constructor>\n" +
                "            </DriverStanding>\n" +
                "            <DriverStanding position=\"6\" positionText=\"6\" points=\"8\" wins=\"0\">\n" +
                "                <Driver driverId=\"kevin_magnussen\" code=\"MAG\" url=\"http://en.wikipedia.org/wiki/Kevin_Magnussen\">\n" +
                "                    <PermanentNumber>20</PermanentNumber>\n" +
                "                    <GivenName>Kevin</GivenName>\n" +
                "                    <FamilyName>Magnussen</FamilyName>\n" +
                "                    <DateOfBirth>1992-10-05</DateOfBirth>\n" +
                "                    <Nationality>Danish</Nationality>\n" +
                "                </Driver>\n" +
                "                <Constructor constructorId=\"haas\" url=\"http://en.wikipedia.org/wiki/Haas_F1_Team\">\n" +
                "                    <Name>Haas F1 Team</Name>\n" +
                "                    <Nationality>American</Nationality>\n" +
                "                </Constructor>\n" +
                "            </DriverStanding>\n" +
                "        </StandingsList>\n" +
                "    </StandingsTable>\n" +
                "</MRData>";
    }
}
