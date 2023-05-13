package de.tim0_12432.f1_schedule_app.data.entity.builder;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;

import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Race;

public class RaceBuilderTest {

    RaceBuilder builder;

    @Before
    public void setup() {
        builder = new RaceBuilder();
    }

    @After
    public void teardown() {
        builder = null;
    }

    @Test
    public void testDefault() {
        Race race = builder.build();
        Assert.assertNull(race.getName());
        Assert.assertNull(race.getUrl());
        Assert.assertNull(race.getCircuit());
        Assert.assertNull(race.getResults());
        Assert.assertNull(race.getTime());
        Assert.assertEquals(-1, race.getRound());
        Assert.assertEquals(-1, race.getSeason());
        Assert.assertEquals("1970-01-01", race.getDate().toString());
    }

    @Test
    public void testAttributes() {
        String url = "http://www.google.de";
        String name = "Test";
        Circuit circuit = new Circuit(null, "Circuit", "https://city.org");
        Date date = new Date(0);
        Time time = new Time(0);
        int round = 9;
        int season = 1997;
        builder = builder
                .withUrl(url)
                .withName(name)
                .withRound(round)
                .withSeason(season)
                .withCircuit(circuit)
                .withDate(date)
                .withTime(time);
        Race race = builder.build();
        Assert.assertEquals(url, race.getUrl());
        Assert.assertEquals(name, race.getName());
        Assert.assertEquals(round, race.getRound());
        Assert.assertEquals(season, race.getSeason());
        Assert.assertNull(race.getResults());
        Assert.assertEquals("1970-01-03", race.getDate().toString());
        Assert.assertEquals(0, race.getTime().getTime());
        Assert.assertEquals(circuit, race.getCircuit());
    }
}
