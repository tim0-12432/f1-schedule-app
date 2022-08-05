package de.tim0_12432.f1_schedule_app.data.entity.builder;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.data.entity.Constructor;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResult;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultStatus;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RaceResultBuilderTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testDefault() {
        RaceResultBuilder builder = new RaceResultBuilder();
        RaceResult driv = builder.build();
        Assert.assertNull(driv.getDriver());
        Assert.assertNull(driv.getStatus());
        Assert.assertNull(driv.getTeam());
        Assert.assertNull(driv.getRaceTime());
        Assert.assertNull(driv.getFastestLapTime());
        Assert.assertNull(driv.getFastestLapSpeed());
        Assert.assertEquals(-1, driv.getNumber());
        Assert.assertEquals(-1, driv.getPosition());
        Assert.assertEquals(-1, driv.getPoints());
        Assert.assertEquals(-1, driv.getGrid());
        Assert.assertEquals(-1, driv.getLaps());
    }

    @Test
    public void testAttributes() {
        Driver driver = new DriverBuilder().build();
        Constructor team = new ConstructorBuilder().build();
        RaceResultStatus status = RaceResultStatus.FINISHED;
        String raceTime = "52:42.002";
        String fastestLapTime = "1:32.028";
        String fastestLapSpeed = "261.67";
        int number = 1;
        int position = 2;
        int points = 3;
        int grid = 4;
        int laps = 5;
        RaceResultBuilder builder = new RaceResultBuilder()
                .withDriver(driver)
                .withTeam(team)
                .withStatus(status)
                .withRaceTime(raceTime)
                .withFastestLapTime(fastestLapTime)
                .withFastestLapSpeed(fastestLapSpeed)
                .withNumber(number)
                .withPosition(position)
                .withPoints(points)
                .withGrid(grid)
                .withLaps(laps);
        RaceResult driv = builder.build();
        Assert.assertEquals(driver, driv.getDriver());
        Assert.assertEquals(status, driv.getStatus());
        Assert.assertEquals(team, driv.getTeam());
        Assert.assertEquals(raceTime, driv.getRaceTime());
        Assert.assertEquals(fastestLapTime, driv.getFastestLapTime());
        Assert.assertEquals(fastestLapSpeed, driv.getFastestLapSpeed());
        Assert.assertEquals(number, driv.getNumber());
        Assert.assertEquals(position, driv.getPosition());
        Assert.assertEquals(points, driv.getPoints());
        Assert.assertEquals(grid, driv.getGrid());
        Assert.assertEquals(laps, driv.getLaps());
    }
}
