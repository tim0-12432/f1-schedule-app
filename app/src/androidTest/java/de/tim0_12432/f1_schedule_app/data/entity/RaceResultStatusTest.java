package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RaceResultStatusTest {
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
