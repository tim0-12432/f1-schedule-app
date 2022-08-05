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
import de.tim0_12432.f1_schedule_app.data.entity.Constructor;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ConstructorBuilderTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testDefault() {
        ConstructorBuilder builder = new ConstructorBuilder();
        Constructor constructor = builder.build();
        Assert.assertNull(constructor.getName());
        Assert.assertNull(constructor.getUrl());
        Assert.assertEquals(Nationality.DEFAULT, constructor.getNationality());
    }

    @Test
    public void testAttributes() {
        String name = "name";
        String nationionality = "American";
        String url = "url";
        ConstructorBuilder builder = new ConstructorBuilder()
                .withUrl(url)
                .withName(name)
                .withNationality(nationionality);
        Constructor constructor = builder.build();
        Assert.assertEquals(url, constructor.getUrl());
        Assert.assertEquals(name, constructor.getName());
        Assert.assertEquals(nationionality.toUpperCase(Locale.ROOT), constructor.getNationality().name());
    }
}
