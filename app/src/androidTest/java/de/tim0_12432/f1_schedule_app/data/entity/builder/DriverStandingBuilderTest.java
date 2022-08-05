package de.tim0_12432.f1_schedule_app.data.entity.builder;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Date;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.data.entity.Constructor;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class DriverStandingBuilderTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testDefault() {
        DriverStandingBuilder builder = new DriverStandingBuilder();
        DriverStanding standing = builder.build();
        Assert.assertNull(standing.getDriver());
        Assert.assertNull(standing.getConstructor());
        Assert.assertEquals(0, standing.getPoints());
        Assert.assertEquals(0, standing.getWins());
        Assert.assertEquals(0, standing.getPosition());
    }

    @Test
    public void testAttributes() {
        Driver driver = new Driver("TEST", "Test", "Test", new Date(0), 56, Nationality.AMERICAN, "https://hello-world.com");
        Constructor constructor = new Constructor("Test", Nationality.ITALIAN, "https://hello-world.com");
        int position = 1;
        int points = 2;
        int wins = 3;
        DriverStandingBuilder builder = new DriverStandingBuilder()
                .withDriver(driver)
                .withConstructor(constructor)
                .withPoints(points)
                .withPosition(position)
                .withWins(wins);
        DriverStanding standing = builder.build();
        Assert.assertEquals(driver, standing.getDriver());
        Assert.assertEquals(constructor, standing.getConstructor());
        Assert.assertEquals(position, standing.getPosition());
        Assert.assertEquals(points, standing.getPoints());
        Assert.assertEquals(wins, standing.getWins());
    }
}
