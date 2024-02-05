package de.tim0_12432.f1_schedule_app.data.entity;

public enum EngineConstructor {
    Honda("Honda"),
    Mercedes("Mercedes-AMG Petronas"),
    Ferrari("Ferrari"),
    Renault("Renault"),
    RBPT("Red Bull Powertrains");

    private final String name;

    EngineConstructor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
