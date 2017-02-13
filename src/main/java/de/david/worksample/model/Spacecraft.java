package de.david.worksample.model;

import java.util.Date;

public class Spacecraft {

    static long lastId = 1L;

    private final long id = Spacecraft.lastId++;
    private String identification = "";
    private String captain = "";
    private Date commissioning = new Date();
    private boolean available = false;
    private SpacecraftType type = SpacecraftType.FERRY;

    public long getId() {
        return this.id;
    }

    public Date getCommissioning() {
        return this.commissioning;
    }

    public String getCaptain() {
        return this.captain;
    }

    public String getIdentification() {
        return this.identification;
    }

    public SpacecraftType getType() {
        return this.type;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setCommissioning(
            final Date commissioning) {
        this.commissioning = commissioning;
    }

    public void setCaptain(
            final String captain) {
        this.captain = captain;
    }

    public void setIdentification(
            final String identification) {
        this.identification = identification;
    }

    public void setType(
            final SpacecraftType typ) {
        this.type = typ;
    }

    public void setAvailable(
            final boolean available) {
        this.available = available;
    }

}
