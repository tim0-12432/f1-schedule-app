package de.tim0_12432.f1_schedule_app.data.parser;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.ConstructorStanding;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class TeamRankingParserTest extends AbstractParserTest {

    private TeamRankingParser parser;

    @Before
    public void setUp() {
        super.setUp();
        parser = new TeamRankingParser();
    }

    @Test
    public void testParse() throws Exception {
        List<ConstructorStanding> list = parser.convert(stream);
        Assert.assertEquals(4, list.size());
        ConstructorStanding standing = list.get(2);
        Assert.assertEquals(15, standing.getPoints());
        Assert.assertEquals(0, standing.getWins());
        Assert.assertEquals("Red Bull", standing.getConstructor().getName());
        Assert.assertEquals(Nationality.AUSTRIAN, standing.getConstructor().getNationality());
    }

    @Test
    public void testParseError() throws Exception {
        List<ConstructorStanding> list = parser.convert(AbstractParserTest.ERROR_STREAM);
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
                "<MRData xmlns=\"http://ergast.com/mrd/1.4\" series=\"f1\" url=\"http://ergast.com/api/f1/current/constructorStandings\" limit=\"30\" offset=\"0\" total=\"4\">\n" +
                "    <StandingsTable season=\"2019\" round=\"1\">\n" +
                "        <StandingsList season=\"2019\" round=\"1\">\n" +
                "            <ConstructorStanding position=\"1\" positionText=\"1\" points=\"44\" wins=\"1\">\n" +
                "                <Constructor constructorId=\"mercedes\" url=\"http://en.wikipedia.org/wiki/Mercedes-Benz_in_Formula_One\">\n" +
                "                    <Name>Mercedes</Name>\n" +
                "                    <Nationality>German</Nationality>\n" +
                "                </Constructor>\n" +
                "            </ConstructorStanding>\n" +
                "            <ConstructorStanding position=\"2\" positionText=\"2\" points=\"22\" wins=\"0\">\n" +
                "                <Constructor constructorId=\"ferrari\" url=\"http://en.wikipedia.org/wiki/Scuderia_Ferrari\">\n" +
                "                    <Name>Ferrari</Name>\n" +
                "                    <Nationality>Italian</Nationality>\n" +
                "                </Constructor>\n" +
                "            </ConstructorStanding>\n" +
                "            <ConstructorStanding position=\"3\" positionText=\"3\" points=\"15\" wins=\"0\">\n" +
                "                <Constructor constructorId=\"red_bull\" url=\"http://en.wikipedia.org/wiki/Red_Bull_Racing\">\n" +
                "                    <Name>Red Bull</Name>\n" +
                "                    <Nationality>Austrian</Nationality>\n" +
                "                </Constructor>\n" +
                "            </ConstructorStanding>\n" +
                "            <ConstructorStanding position=\"4\" positionText=\"4\" points=\"8\" wins=\"0\">\n" +
                "                <Constructor constructorId=\"haas\" url=\"http://en.wikipedia.org/wiki/Haas_F1_Team\">\n" +
                "                    <Name>Haas F1 Team</Name>\n" +
                "                    <Nationality>American</Nationality>\n" +
                "                </Constructor>\n" +
                "            </ConstructorStanding>\n" +
                "        </StandingsList>\n" +
                "    </StandingsTable>\n" +
                "</MRData>";
    }
}
