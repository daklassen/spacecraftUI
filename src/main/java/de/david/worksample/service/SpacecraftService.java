package de.david.worksample.service;

import java.util.List;

import de.david.worksample.model.SpacecraftType;

public interface SpacecraftService {
    SpacecraftTO createSpacecraft(
            SpacecraftTO spacecraftTO);

    void deleteSpacecraft(long id) throws SpacecraftServiceException;

    SpacecraftTO getSpacecraft(long id) throws SpacecraftServiceException;

    List<SpacecraftTO> getSpacecrafts();

    List<SpacecraftTO> getSpacecrafts(SpacecraftType type);

    void setSpacecraft(SpacecraftTO spacecraftTO) throws SpacecraftServiceException;

}
