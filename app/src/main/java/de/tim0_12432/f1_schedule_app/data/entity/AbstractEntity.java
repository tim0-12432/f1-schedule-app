package de.tim0_12432.f1_schedule_app.data.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    @NonNull
    @Override
    public abstract String toString();
}
