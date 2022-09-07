package de.tim0_12432.f1_schedule_app.data.entity.builder;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;

import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Race;

public class RaceBuilderTest {
    @Test
    public void testDefault() {
        RaceBuilder builder = new RaceBuilder();
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
        RaceBuilder builder = new RaceBuilder()
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
        Assert.assertEquals(time, race.getTime());
        Assert.assertEquals(circuit, race.getCircuit());
    }
}
