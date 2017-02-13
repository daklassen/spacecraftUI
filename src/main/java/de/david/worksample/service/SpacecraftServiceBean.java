package de.david.worksample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.david.worksample.model.Spacecraft;
import de.david.worksample.model.SpacecraftType;

public class SpacecraftServiceBean
        implements SpacecraftService {

    static HashMap<Long, Spacecraft> spacecrafts = new HashMap<Long, Spacecraft>();

    static protected void deleteAll() {
        SpacecraftServiceBean.spacecrafts.clear();
    }

    private void copyToSpacecraft(final SpacecraftTO to, final Spacecraft spacecraft) {
        spacecraft.setIdentification(to.identification);
        spacecraft.setCommissioning(to.commissioning);
        spacecraft.setCaptain(to.captain);
        spacecraft.setType(to.type);
        spacecraft.setAvailable(to.available);
    }

    @Override
    public SpacecraftTO createSpacecraft(final SpacecraftTO spacecraftTO) {
        final Spacecraft spacecraft = new Spacecraft();

        copyToSpacecraft(spacecraftTO, spacecraft);

        SpacecraftServiceBean.spacecrafts.put(spacecraft.getId(), spacecraft);

        return createSpacecraftTO(spacecraft);
    }

    private SpacecraftTO createSpacecraftTO(final Spacecraft spacecraft) {
        final SpacecraftTO to = new SpacecraftTO();

        to.id = spacecraft.getId();
        to.identification = spacecraft.getIdentification();
        to.commissioning = spacecraft.getCommissioning();
        to.captain = spacecraft.getCaptain();
        to.type = spacecraft.getType();
        to.available = spacecraft.isAvailable();

        return to;
    }

    @Override
    public void deleteSpacecraft(final long id) throws SpacecraftServiceException {
        final Spacecraft spacecraft = SpacecraftServiceBean.spacecrafts.get(id);

        if (spacecraft == null) {
            throw new SpacecraftServiceException(
                    "A spacecraft with ID "
                            + id
                            + " was not found.");
        }

        SpacecraftServiceBean.spacecrafts.remove(spacecraft.getId());
    }

    @Override
    public SpacecraftTO getSpacecraft(
            final long id)
            throws SpacecraftServiceException {
        final Spacecraft spacecraft = SpacecraftServiceBean.spacecrafts.get(id);

        if (spacecraft == null) {
            throw new SpacecraftServiceException(
                    "A spacecraft with ID "
                            + id
                            + " was not found.");
        }

        return createSpacecraftTO(spacecraft);
    }

    @Override
    public List<SpacecraftTO> getSpacecrafts() {
        final List<SpacecraftTO> spacecraftTOs = new ArrayList<SpacecraftTO>();

        for (final Spacecraft spacecraft : SpacecraftServiceBean.spacecrafts
                .values()) {
            spacecraftTOs.add(createSpacecraftTO(spacecraft));
        }

        return spacecraftTOs;
    }

    @Override
    public List<SpacecraftTO> getSpacecrafts(final SpacecraftType typ) {
        final List<SpacecraftTO> spacecraftTOs = new ArrayList<>();

        for (final Spacecraft spacecraft : SpacecraftServiceBean.spacecrafts
                .values()) {
            if (spacecraft.getType().equals(typ)) {
                spacecraftTOs.add(createSpacecraftTO(spacecraft));
            }
        }

        return spacecraftTOs;
    }

    @Override
    public void setSpacecraft(final SpacecraftTO spacecraftTO) throws SpacecraftServiceException {
        final Spacecraft raumschiff = SpacecraftServiceBean.spacecrafts
                .get(spacecraftTO.id);

        if (raumschiff == null) {
            throw new SpacecraftServiceException(
                    "A spacecraft with ID "
                            + spacecraftTO.id
                            + " was not found.");
        }

        copyToSpacecraft(spacecraftTO, raumschiff);
    }
}
