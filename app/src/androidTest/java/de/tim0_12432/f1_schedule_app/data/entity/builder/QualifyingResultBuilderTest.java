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
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.QualifyingResult;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class QualifyingResultBuilderTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testDefault() {
        QualifyingResultBuilder builder = new QualifyingResultBuilder();
        QualifyingResult quali = builder.build();
        Assert.assertNull(quali.getDriver());
        Assert.assertNull(quali.getTeam());
        Assert.assertEquals(-1, quali.getNumber());
        Assert.assertEquals(-1, quali.getPosition());
        Assert.assertEquals(3, quali.getLapTimes().length);
        Assert.assertNull(quali.getLapTimes()[0]);
        Assert.assertNull(quali.getFastestLapTime());
    }

    @Test
    public void testAttributes() {
        Driver driver = new DriverBuilder().build();
        Constructor team = new ConstructorBuilder().build();
        String q1 = "01:02.028";
        String q2 = "00:52.002";
        int number = 1;
        int position = 2;
        QualifyingResultBuilder builder = new QualifyingResultBuilder()
                .withDriver(driver)
                .withTeam(team)
                .withNumber(number)
                .withPosition(position)
                .withQ1(q1)
                .withQ2(q2);
        QualifyingResult quali = builder.build();
        Assert.assertEquals(driver, quali.getDriver());
        Assert.assertEquals(team, quali.getTeam());
        Assert.assertEquals(number, quali.getNumber());
        Assert.assertEquals(position, quali.getPosition());
        Assert.assertEquals(q1, quali.getLapTimes()[0]);
        Assert.assertEquals(q2, quali.getLapTimes()[1]);
        Assert.assertNull(quali.getLapTimes()[2]);
        Assert.assertEquals(q2, quali.getFastestLapTime());
    }
}
