package de.tim0_12432.f1_schedule_app.data.parser;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResult;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class RaceResultsParserTest extends AbstractParserTest {

    private RaceResultsParser parser;

    @Override
    protected String getXmlFileName() {
        return "assets/race_results.xml";
    }

    @Before
    public void setUp() throws IOException {
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
}
