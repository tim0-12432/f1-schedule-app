package de.tim0_12432.f1_schedule_app.data.parser;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import java.io.InputStream;

import de.tim0_12432.f1_schedule_app.MainActivity;

@MediumTest
@RunWith(AndroidJUnit4.class)
public abstract class AbstractParserTest {
    @Rule
    public final ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    protected static final InputStream ERROR_STREAM = IOUtils.toInputStream("<html><h1>Error reading response from server!</h1></html>", "UTF-8");

    protected abstract String getXmlString();

    protected InputStream stream;

    @Before
    public void setUp() {
        stream = IOUtils.toInputStream(getXmlString(), "UTF-8");
    }

    @After
    public void tearDown() {
        stream = null;
    }
}