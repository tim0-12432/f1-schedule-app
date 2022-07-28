package de.tim0_12432.f1_schedule_app.data.source;

import java.util.List;

public interface LoadCallback<T> {
    void onLoaded(List<T> list);
}
