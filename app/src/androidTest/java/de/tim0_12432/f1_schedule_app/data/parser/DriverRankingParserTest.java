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

import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class DriverRankingParserTest extends AbstractParserTest {

    private DriverRankingParser parser;

    @Override
    protected String getXmlFileName() {
        return "assets/driver_standings.xml";
    }

    @Before
    public void setUp() throws IOException {
        super.setUp();
        parser = new DriverRankingParser();
    }

    @Test
    public void testParse() throws Exception {
        List<DriverStanding> list = parser.convert(stream);
        Assert.assertEquals(6, list.size());
        DriverStanding standing = list.get(5);
        Assert.assertEquals("Charles", standing.getDriver().getName());
        Assert.assertEquals(10, standing.getPoints());
        Assert.assertEquals("Ferrari", standing.getConstructor().getName());
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
}
