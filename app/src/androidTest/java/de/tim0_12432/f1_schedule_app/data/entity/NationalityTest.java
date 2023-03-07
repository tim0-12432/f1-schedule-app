package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.UiContext;
import androidx.test.espresso.internal.inject.InstrumentationContext;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.filters.SmallTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class NationalityTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testAttributes() {
        Assert.assertEquals(R.string.nationality_default, Nationality.DEFAULT.getTranslationId());
        Assert.assertEquals("\uD83C\uDFF3\u200D\uD83C\uDF08", Nationality.DEFAULT.getEmojiFlag());
    }

    @Test
    public void testGetNationalityOfCountry() {
        Assert.assertEquals(Nationality.USAMERICAN, Nationality.getNationalityOfCountry("USA"));
        Assert.assertEquals(Nationality.CHINESE, Nationality.getNationalityOfCountry("China"));
        Assert.assertEquals(Nationality.DEFAULT, Nationality.getNationalityOfCountry("Abcdefghijk"));
    }

    @Test
    public void testGetNationalityOfTranslation() {
        Assert.assertEquals(Nationality.ARAB, Nationality.getNationalityOfTranslation("Arab"));
        Assert.assertEquals(Nationality.ITALIAN, Nationality.getNationalityOfTranslation("italian"));
        Assert.assertEquals(Nationality.DEFAULT, Nationality.getNationalityOfTranslation("Abcdefghijk"));
    }
}
