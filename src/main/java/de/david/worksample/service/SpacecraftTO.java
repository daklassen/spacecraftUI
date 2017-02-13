package de.david.worksample.service;

import java.util.Date;

import de.david.worksample.model.SpacecraftType;

public class SpacecraftTO {
    public long id;
    public String identification;
    public String captain;
    public Date commissioning;
    public boolean available;
    public SpacecraftType type;

    public SpacecraftTO() {
    }

    public SpacecraftTO(String identification, String captain, Date commissioning, boolean available, SpacecraftType type) {
        this.identification = identification;
        this.captain = captain;
        this.commissioning = commissioning;
        this.available = available;
        this.type = type;
    }
}
