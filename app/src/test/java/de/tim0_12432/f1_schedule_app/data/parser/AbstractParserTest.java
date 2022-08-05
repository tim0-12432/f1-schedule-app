package de.tim0_12432.f1_schedule_app.data.parser;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public abstract class AbstractParserTest {
    protected static final InputStream ERROR_STREAM = IOUtils.toInputStream("<html><h1>Error reading response from server!</h1></html>", "UTF-8");

    protected abstract String getXmlFileName();

    protected InputStream stream;

    @Before
    public void setUp() throws IOException {
        File file = FileUtils.getFile("src", "test", "res", getXmlFileName());
        System.out.println(FileUtils.readFileToString(file, StandardCharsets.UTF_8));
        stream = FileUtils.openInputStream(file);
    }

    @After
    public void tearDown() {
        stream = null;
    }
}
