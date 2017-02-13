package de.david.worksample.model;

public enum SpacecraftType {
    FREIGHTER("Freighter"),
    FRIGATE("Frigate"),
    CRUISER("Cruiser"),
    FERRY("Ferry");

    private String bezeichnung;

    private SpacecraftType(
            final String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public String toString() {
        return this.bezeichnung;
    }
}
