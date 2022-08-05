package de.tim0_12432.f1_schedule_app.data.entity.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import de.tim0_12432.f1_schedule_app.data.entity.Entity;

public abstract class EntityParser<T extends Entity> {
    public abstract T parse(XmlPullParser parser) throws XmlPullParserException, IOException;
}
