package de.tim0_12432.f1_schedule_app.data.parser;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.List;

import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class DriverRankingParserTest extends AbstractParserTest {

    private DriverRankingParser parser;

    @Override
    protected String getXmlFileName() {
        return "driver_standings.xml";
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
        List<DriverStanding> list = parser.convert(ERROR_STREAM);
        Assert.assertEquals(0, list.size());
    }

    @After
    public void tearDown() {
        super.tearDown();
        parser = null;
    }
}
