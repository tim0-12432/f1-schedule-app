package de.tim0_12432.f1_schedule_app.data.entity.builder;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResult;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultList;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class RaceResultListBuilderTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testDefault() {
        RaceResultListBuilder builder = new RaceResultListBuilder();
        RaceResultList list = builder.build();
        Assert.assertEquals(Collections.emptyList(), list.getResults());
    }

    @Test
    public void testAttributes() {
        int amount = 10;
        RaceResultListBuilder builder = new RaceResultListBuilder();
        for (int i = 0; i < amount; i++) {
            builder.withRaceResult(new RaceResultBuilder().withPosition(i).build());
        }
        RaceResultList list = builder.build();
        List<RaceResult> results = list.getResults();
        Assert.assertEquals(amount, results.size());
        for (int i = 0; i < amount; i++) {
            Assert.assertEquals(i, results.get(i).getPosition());
        }
    }
}
