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

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class CircuitsParserTest extends AbstractParserTest {

    private CircuitsParser parser;

    @Override
    protected String getXmlFileName() {
        return "circuits.xml";
    }

    @Before
    public void setUp() throws IOException {
        super.setUp();
        parser = new CircuitsParser();
    }

    @Test
    public void testParse() throws Exception {
        List<Circuit> list = parser.convert(stream);
        Assert.assertEquals(2, list.size());
        Circuit circuit = list.get(0);
        Assert.assertEquals("Test circuit 1", circuit.getName());
        Assert.assertEquals("Italy", circuit.getLocation().getCountry());
        Assert.assertEquals("Monza", circuit.getLocation().getLocality());
    }

    @Test
    public void testParseError() throws Exception {
        List<Circuit> list = parser.convert(ERROR_STREAM);
        Assert.assertEquals(0, list.size());
    }

    @After
    public void tearDown() {
        super.tearDown();
        parser = null;
    }
}
