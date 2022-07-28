package de.tim0_12432.f1_schedule_app.data.entity.practice;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.time.LocalDate;
import java.time.LocalTime;

@Root(name="SecondPractice")
public class SecondPractice extends Practice {
    public SecondPractice(@Element(name="Date") String date, @Element(name="Time") String time) {
        super(date, time);
    }

    public SecondPractice(@Element(name="Date") LocalDate date, @Element(name="Time") LocalTime time) {
        super(date, time);
    }
}
