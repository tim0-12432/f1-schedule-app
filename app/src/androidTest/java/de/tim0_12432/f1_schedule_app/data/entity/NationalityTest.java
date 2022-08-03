package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.tim0_12432.f1_schedule_app.R;

@RunWith(AndroidJUnit4.class)
public class NationalityTest {
    @Test
    public void testAttributes() {
        Assert.assertEquals(R.string.nationality_default, Nationality.DEFAULT.getTranslationId());
        Assert.assertEquals("\uD83C\uDFF3\u200D\uD83C\uDF08", Nationality.DEFAULT.getEmojiFlag());
    }

    @Test
    public void testGetNationalityOfCountry() {
        Assert.assertEquals(Nationality.AMERICAN, Nationality.getNationalityOfCountry("USA"));
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
