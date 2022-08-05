package de.tim0_12432.f1_schedule_app.data.entity.builder;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Date;
import java.util.Locale;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.Location;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DriverBuilderTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testDefault() {
        DriverBuilder builder = new DriverBuilder();
        Driver driv = builder.build();
        Assert.assertNull(driv.getName());
        Assert.assertNull(driv.getFamilyName());
        Assert.assertNull(driv.getCode());
        Assert.assertNull(driv.getUrl());
        Assert.assertNull(driv.getDateOfBirth());
        Assert.assertEquals(Nationality.DEFAULT, driv.getNationality());
        Assert.assertEquals(-1, driv.getNumber());
    }

    @Test
    public void testAttributes() {
        String code = "code";
        String name = "name";
        String familyName = "familyName";
        Date dateOfBirth = new Date(0);
        int number = 1;
        String nationionality = "American";
        String url = "url";
        DriverBuilder builder = new DriverBuilder()
                .withUrl(url)
                .withName(name)
                .withFamilyName(familyName)
                .withCode(code)
                .withDateOfBirth(dateOfBirth)
                .withNumber(number)
                .withNationality(nationionality);
        Driver driv = builder.build();
        Assert.assertEquals(url, driv.getUrl());
        Assert.assertEquals(name, driv.getName());
        Assert.assertEquals(familyName, driv.getFamilyName());
        Assert.assertEquals(code, driv.getCode());
        Assert.assertEquals(dateOfBirth, driv.getDateOfBirth());
        Assert.assertEquals(number, driv.getNumber());
        Assert.assertEquals(nationionality.toUpperCase(Locale.ROOT), driv.getNationality().name());
    }
}
