package de.tim0_12432.f1_schedule_app.data.entity.builder;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.Location;

public class DriverBuilderTest {
    @Test
    public void testDefault() {
        DriverBuilder builder = new DriverBuilder();
        Driver driv = builder.build();
        Assert.assertNull(driv.getName());
        Assert.assertNull(driv.getFamilyName());
        Assert.assertNull(driv.getCode());
        Assert.assertNull(driv.getUrl());
        Assert.assertNull(driv.getDateOfBirth());
        Assert.assertNull(driv.getNationality());
        Assert.assertEquals(-1, driv.getNumber());
    }

    @Test
    public void testAttributes() {
        String code = "code";
        String name = "name";
        String familyName = "familyName";
        Date dateOfBirth = new Date(0);
        int number = 1;
        String nation = "USA";
        String url = "url";
        DriverBuilder builder = new DriverBuilder()
                .withUrl(url)
                .withName(name)
                .withFamilyName(familyName)
                .withCode(code)
                .withDateOfBirth(dateOfBirth)
                .withNumber(number)
                .withNationality(nation);
        Driver driv = builder.build();
        Assert.assertEquals(url, driv.getUrl());
        Assert.assertEquals(name, driv.getName());
        Assert.assertEquals(familyName, driv.getFamilyName());
        Assert.assertEquals(code, driv.getCode());
        Assert.assertEquals(dateOfBirth, driv.getDateOfBirth());
        Assert.assertEquals(number, driv.getNumber());
        Assert.assertEquals(nation, driv.getNationality().name());
    }
}
