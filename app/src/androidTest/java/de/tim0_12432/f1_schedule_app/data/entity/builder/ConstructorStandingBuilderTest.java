package de.tim0_12432.f1_schedule_app.data.entity.builder;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.data.entity.Constructor;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorStanding;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class ConstructorStandingBuilderTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testDefault() {
        ConstructorStandingBuilder builder = new ConstructorStandingBuilder();
        ConstructorStanding standing = builder.build();
        Assert.assertNull(standing.getConstructor());
        Assert.assertEquals(0, standing.getPoints());
        Assert.assertEquals(0, standing.getWins());
        Assert.assertEquals(0, standing.getPosition());
    }

    @Test
    public void testAttributes() {
        Constructor constructor = new Constructor("Test", Nationality.ITALIAN, "https://hello-world.com");
        int position = 1;
        int points = 2;
        int wins = 3;
        ConstructorStandingBuilder builder = new ConstructorStandingBuilder()
                .withConstructor(constructor)
                .withPoints(points)
                .withPosition(position)
                .withWins(wins);
        ConstructorStanding standing = builder.build();
        Assert.assertEquals(constructor, standing.getConstructor());
        Assert.assertEquals(position, standing.getPosition());
        Assert.assertEquals(points, standing.getPoints());
        Assert.assertEquals(wins, standing.getWins());
    }
}
