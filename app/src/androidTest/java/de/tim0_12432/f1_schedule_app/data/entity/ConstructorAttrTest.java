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
public class ConstructorAttrTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testAttributes() {
        Assert.assertEquals(Nationality.ITALIAN, ConstructorAttr.FERR.getNationality());
        Assert.assertEquals("Red Bull Racing", ConstructorAttr.REDB.getName());
        Assert.assertEquals(EngineConstructor.Mercedes, ConstructorAttr.MERC.getEngine());
    }

    @Test
    public void testGetConstructorByName() {
        Assert.assertEquals(ConstructorAttr.DEFAULT, ConstructorAttr.getConstructorByName("Afcbfsd"));
        Assert.assertEquals(ConstructorAttr.FERR, ConstructorAttr.getConstructorByName("Ferrari"));
        Assert.assertEquals(ConstructorAttr.REDB, ConstructorAttr.getConstructorByName("Red Bull"));
    }

    @Test
    public void testGetConstructorOfTeam() {
        Assert.assertEquals(ConstructorAttr.FERR, ConstructorAttr.getConstructorOfTeam(new Constructor("Ferrari", Nationality.ITALIAN, "url")));
        Assert.assertEquals(ConstructorAttr.DEFAULT, ConstructorAttr.getConstructorOfTeam(new Constructor("Absfdsf", Nationality.AUSTRALIAN, "url")));
    }
}
