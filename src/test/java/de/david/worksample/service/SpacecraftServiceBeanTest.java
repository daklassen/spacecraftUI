package de.david.worksample.service;/*
 * Copyright (c) 2014 by OKIT GmbH.
 * All rights reserved.
 *
 * Diese Software ist urheberrechtlich geschützt.
 *
 * Project: work sample
 */


import java.util.Date;
import java.util.List;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.david.worksample.model.SpacecraftType;

/**
 * @author Olaf Krause
 */
public class SpacecraftServiceBeanTest {

    static final String DEFAULT_CAPTAIN = "James Tiberius Kirk";
    static final String DEFAULT_IDENTIFICATION = "NCC 1701";

    SpacecraftService spacecraftService = new SpacecraftServiceBean();

    /**
     * @throws Exception
     */
    @Before
    public void setUp()
            throws Exception
    {
        SpacecraftServiceBean.deleteAll();
    }

    /**
     * @throws Exception
     */
    @After
    public void tearDown()
            throws Exception
    {
        SpacecraftServiceBean.deleteAll();
    }

    /**
     * Test method for
     * {@link SpacecraftServiceBean#createSpacecraft(SpacecraftTO)}
     * .
     */
    @Test
    public void testCreateRaumschiff() {
        final Date indienststellung = new Date();
        final SpacecraftTO spacecraftTOIn = new SpacecraftTO();
        spacecraftTOIn.identification = SpacecraftServiceBeanTest.DEFAULT_IDENTIFICATION;
        spacecraftTOIn.commissioning = indienststellung;
        spacecraftTOIn.captain = SpacecraftServiceBeanTest.DEFAULT_CAPTAIN;
        spacecraftTOIn.available = true;
        spacecraftTOIn.type = SpacecraftType.FREIGHTER;

        final SpacecraftTO spacecraftTOOut = this.spacecraftService
                .createSpacecraft(spacecraftTOIn);

        Assert.assertEquals(spacecraftTOIn.identification,
                spacecraftTOOut.identification);
        Assert.assertEquals(indienststellung, spacecraftTOOut.commissioning);
        Assert.assertEquals(spacecraftTOIn.captain, spacecraftTOOut.captain);
        Assert.assertEquals(spacecraftTOIn.available, spacecraftTOOut.available);
        Assert.assertTrue(spacecraftTOOut.id >= 1);
    }

    /**
     * Test method for
     * {@link SpacecraftServiceBean#deleteSpacecraft(long)}
     * .
     * 
     * @throws SpacecraftServiceException
     */
    @Test
    public void testDeleteSpacecraft()
            throws SpacecraftServiceException
    {
        final Date indienststellung = new Date();
        final SpacecraftTO spacecraftTOIn = new SpacecraftTO();
        spacecraftTOIn.identification = SpacecraftServiceBeanTest.DEFAULT_IDENTIFICATION;
        spacecraftTOIn.commissioning = indienststellung;
        spacecraftTOIn.captain = SpacecraftServiceBeanTest.DEFAULT_CAPTAIN;
        spacecraftTOIn.available = true;
        spacecraftTOIn.type = SpacecraftType.FREIGHTER;

        final SpacecraftTO spacecraftTOOut = this.spacecraftService
                .createSpacecraft(spacecraftTOIn);

        this.spacecraftService.deleteSpacecraft(spacecraftTOOut.id);

        Assert.assertEquals(0, this.spacecraftService.getSpacecrafts().size());
    }

    /**
     * Test method for
     * {@link SpacecraftServiceBean#getSpacecraft(long)}
     * .
     * 
     * @throws SpacecraftServiceException
     */
    @Test
    public void testGetSpacecraft()
            throws SpacecraftServiceException
    {
        final Date commissioning = new Date();
        final SpacecraftTO spacecraftTOIn = new SpacecraftTO();
        spacecraftTOIn.identification = SpacecraftServiceBeanTest.DEFAULT_IDENTIFICATION;
        spacecraftTOIn.commissioning = commissioning;
        spacecraftTOIn.captain = SpacecraftServiceBeanTest.DEFAULT_CAPTAIN;
        spacecraftTOIn.available = true;
        spacecraftTOIn.type = SpacecraftType.FREIGHTER;

        final long id = this.spacecraftService.createSpacecraft(spacecraftTOIn).id;

        final SpacecraftTO spacecraftTOOut = this.spacecraftService
                .getSpacecraft(id);

        Assert.assertEquals(spacecraftTOIn.identification,
                spacecraftTOOut.identification);
        Assert.assertEquals(commissioning, spacecraftTOOut.commissioning);
        Assert.assertEquals(spacecraftTOIn.captain, spacecraftTOOut.captain);
        Assert.assertEquals(spacecraftTOIn.available, spacecraftTOOut.available);
        Assert.assertTrue(spacecraftTOOut.id >= 1);

    }

    /**
     * Test method for
     * {@link SpacecraftServiceBean#getSpacecrafts()}.
     */
    @Test
    public void testGetSpacecrafts() {
        final Date commissioning = new Date();
        final SpacecraftTO spacecraftTOIn = new SpacecraftTO();
        spacecraftTOIn.identification = SpacecraftServiceBeanTest.DEFAULT_IDENTIFICATION;
        spacecraftTOIn.commissioning = commissioning;
        spacecraftTOIn.captain = SpacecraftServiceBeanTest.DEFAULT_CAPTAIN;
        spacecraftTOIn.available = true;

        spacecraftTOIn.type = SpacecraftType.FREIGHTER;
        this.spacecraftService.createSpacecraft(spacecraftTOIn);

        spacecraftTOIn.type = SpacecraftType.FRIGATE;
        this.spacecraftService.createSpacecraft(spacecraftTOIn);

        spacecraftTOIn.type = SpacecraftType.FERRY;
        this.spacecraftService.createSpacecraft(spacecraftTOIn);

        spacecraftTOIn.type = SpacecraftType.CRUISER;
        this.spacecraftService.createSpacecraft(spacecraftTOIn);

        final List<SpacecraftTO> spacecraftTOs = this.spacecraftService
                .getSpacecrafts();

        Assert.assertEquals(4, spacecraftTOs.size());
    }

    /**
     * Test method for
     * {@link SpacecraftServiceBean#getSpacecrafts(SpacecraftType)}
     * .
     */
    @Test
    public void testGetSpacecraftSpacecraftType() {
        final Date commissioning = new Date();
        final SpacecraftTO spacecraftTOIn = new SpacecraftTO();
        spacecraftTOIn.identification = SpacecraftServiceBeanTest.DEFAULT_IDENTIFICATION;
        spacecraftTOIn.commissioning = commissioning;
        spacecraftTOIn.captain = SpacecraftServiceBeanTest.DEFAULT_CAPTAIN;
        spacecraftTOIn.available = true;

        spacecraftTOIn.type = SpacecraftType.FREIGHTER;
        this.spacecraftService.createSpacecraft(spacecraftTOIn);

        spacecraftTOIn.type = SpacecraftType.FRIGATE;
        this.spacecraftService.createSpacecraft(spacecraftTOIn);

        spacecraftTOIn.type = SpacecraftType.FERRY;
        this.spacecraftService.createSpacecraft(spacecraftTOIn);

        spacecraftTOIn.type = SpacecraftType.CRUISER;
        this.spacecraftService.createSpacecraft(spacecraftTOIn);

        final List<SpacecraftTO> spacecraftTOs = this.spacecraftService
                .getSpacecrafts(SpacecraftType.FRIGATE);

        Assert.assertEquals(1, spacecraftTOs.size());
        Assert.assertEquals(SpacecraftType.FRIGATE, spacecraftTOs.get(0).type);
    }

    /**
     * Test method for
     * {@link SpacecraftServiceBean#setSpacecraft(SpacecraftTO)}
     * .
     * 
     * @throws SpacecraftServiceException
     */
    @Test
    public void testSetSpacecraft()
            throws SpacecraftServiceException
    {
        final Date commissioning = new Date();
        SpacecraftTO spacecraftTOIn = new SpacecraftTO();
        spacecraftTOIn.identification = SpacecraftServiceBeanTest.DEFAULT_IDENTIFICATION;
        spacecraftTOIn.commissioning = commissioning;
        spacecraftTOIn.captain = SpacecraftServiceBeanTest.DEFAULT_CAPTAIN;
        spacecraftTOIn.available = true;
        spacecraftTOIn.type = SpacecraftType.FREIGHTER;

        SpacecraftTO spacecraftTOOut = this.spacecraftService
                .createSpacecraft(spacecraftTOIn);

        spacecraftTOIn = spacecraftTOOut;
        spacecraftTOIn.captain = "Jean-Luc Picard";
        spacecraftTOIn.identification = "NCC 1701D";
        spacecraftTOIn.available = false;
        this.spacecraftService.setSpacecraft(spacecraftTOIn);

        spacecraftTOOut = this.spacecraftService
                .getSpacecraft(spacecraftTOIn.id);

        Assert.assertEquals("NCC 1701D", spacecraftTOOut.identification);
        Assert.assertEquals("Jean-Luc Picard", spacecraftTOOut.captain);
        // Assert.assertEquals(false, spacecraftTOOut.available);
        Assert.assertTrue(spacecraftTOOut.id >= 1);
    }

}
