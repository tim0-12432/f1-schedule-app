package de.tim0_12432.f1_schedule_app.data.entity.practice;

import android.annotation.SuppressLint;

import org.simpleframework.xml.Element;

import java.time.LocalDate;
import java.time.LocalTime;

class Practice {
    @Element(name="Date")
    private LocalDate date;

    @Element(name="Time")
    private LocalTime time;

    @SuppressLint("NewApi")
    public Practice(@Element(name="Date") String date, @Element(name="Time") String time) {
        new Practice(LocalDate.parse(date), LocalTime.parse(time));
    }

    public Practice(@Element(name="Date") LocalDate date, @Element(name="Time") LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}

