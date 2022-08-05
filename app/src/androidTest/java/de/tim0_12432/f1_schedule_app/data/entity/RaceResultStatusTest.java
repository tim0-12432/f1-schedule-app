package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.tim0_12432.f1_schedule_app.MainActivity;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class RaceResultStatusTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testAttributes() {
        Assert.assertEquals(1, RaceResultStatus.FINISHED.getCode());
        Assert.assertEquals("\uD83C\uDFC1", RaceResultStatus.FINISHED.getEmoji());
    }

    @Test
    public void testGetByCode() {
        Assert.assertEquals(RaceResultStatus.FINISHED, RaceResultStatus.getByCode(1));
        Assert.assertEquals(RaceResultStatus.PLUS_1_LAP, RaceResultStatus.getByCode(11));
        Assert.assertEquals(RaceResultStatus.DEFAULT, RaceResultStatus.getByCode(999));
    }
}
