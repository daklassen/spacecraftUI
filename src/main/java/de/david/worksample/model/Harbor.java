package de.david.worksample.model;

import de.david.worksample.service.SpacecraftService;
import de.david.worksample.service.SpacecraftServiceBean;
import de.david.worksample.service.SpacecraftServiceException;
import de.david.worksample.service.SpacecraftTO;
import de.david.worksample.util.DateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Harbor implements SpacecraftService {
    private static Harbor instance = null;

    public static Harbor getInstance() {
        if (instance == null) {
            instance = new Harbor();
        }
        return instance;
    }

    private SpacecraftServiceBean bean;
    private ObservableList<SpacecraftTO> observableSpacecraftList = FXCollections.observableArrayList(new ArrayList<>());

    private SpacecraftTO selectedSpacecraft;

    private StringProperty selectedIdentificationField = new SimpleStringProperty();
    private StringProperty selectedIdentificationLabel = new SimpleStringProperty();
    private StringProperty selectedCaptainField = new SimpleStringProperty();
    private StringProperty selectedCaptainLabel = new SimpleStringProperty();
    private StringProperty selectedCommissioningField = new SimpleStringProperty();
    private StringProperty selectedCommissioningLabel = new SimpleStringProperty();
    private StringProperty selectedAvailableField = new SimpleStringProperty();
    private StringProperty selectedAvailableLabel = new SimpleStringProperty();
    private StringProperty selectedTypeField = new SimpleStringProperty();
    private StringProperty selectedTypeLabel = new SimpleStringProperty();

    private Harbor() {
        bean = new SpacecraftServiceBean();
        observableSpacecraftList.setAll(bean.getSpacecrafts());
        setSelection(null);
    }

    public void setSelection(SpacecraftTO spacecraft) {

        selectedSpacecraft = spacecraft;

        if (spacecraft == null) {
            selectedIdentificationLabel.set("");
            selectedCaptainLabel.set("");
            selectedCommissioningLabel.set("");
            selectedAvailableLabel.set("");
            selectedTypeLabel.set("");

            selectedIdentificationField.set("");
            selectedCaptainField.set("");
            selectedCommissioningField.set("");
            selectedAvailableField.set("");
            selectedTypeField.set("");

        } else {
            selectedIdentificationLabel.set("identification".toUpperCase());
            selectedCaptainLabel.set("captain".toUpperCase());
            selectedCommissioningLabel.set("commissioning".toUpperCase());
            selectedAvailableLabel.set("available".toUpperCase());
            selectedTypeLabel.set("type".toUpperCase());

            selectedIdentificationField.set(selectedSpacecraft.identification);
            selectedCaptainField.set(selectedSpacecraft.captain);
            selectedCommissioningField.set(DateUtil.format(selectedSpacecraft.commissioning));
            selectedTypeField.set(selectedSpacecraft.type.toString());
            selectedAvailableField.set(selectedSpacecraft.available ? "Online" : "Offline");
        }
    }

    public SpacecraftTO getSelectedSpacecraft() {
        return selectedSpacecraft;
    }

    public ObservableList<SpacecraftTO> getObservableSpacecraftList() {
        return observableSpacecraftList;
    }

    @Override
    public SpacecraftTO createSpacecraft(SpacecraftTO spacecraftTO) {
        SpacecraftTO spacecraftTOWithID = null;
        if (spacecraftTO != null) {
            spacecraftTOWithID = bean.createSpacecraft(spacecraftTO);
            observableSpacecraftList.add(spacecraftTOWithID);
        }
        return spacecraftTOWithID;
    }

    @Override
    public void deleteSpacecraft(long id) throws SpacecraftServiceException {
        bean.deleteSpacecraft(id);

        for (SpacecraftTO tempSpacecraft : observableSpacecraftList) {
            if (tempSpacecraft.id == id) {
                observableSpacecraftList.remove(tempSpacecraft);
                break;
            }
        }
    }

    @Override
    public SpacecraftTO getSpacecraft(long id) throws SpacecraftServiceException {
        return bean.getSpacecraft(id);
    }

    @Override
    public List<SpacecraftTO> getSpacecrafts() {
        return bean.getSpacecrafts();
    }

    @Override
    public List<SpacecraftTO> getSpacecrafts(SpacecraftType type) {
        return bean.getSpacecrafts(type);
    }

    @Override
    public void setSpacecraft(SpacecraftTO spacecraftTO) throws SpacecraftServiceException {
        bean.setSpacecraft(spacecraftTO);

        for (SpacecraftTO tempSpacecraft : observableSpacecraftList) {
            if (tempSpacecraft.id == spacecraftTO.id) {
                tempSpacecraft.identification = spacecraftTO.identification;
                tempSpacecraft.captain = spacecraftTO.captain;
                tempSpacecraft.commissioning = spacecraftTO.commissioning;
                tempSpacecraft.type = spacecraftTO.type;
                tempSpacecraft.available = spacecraftTO.available;
                break;
            }
        }
    }

    public StringProperty getSelectedIdentificationField() {
        return selectedIdentificationField;
    }

    public StringProperty selectedIdentificationFieldProperty() {
        return selectedIdentificationField;
    }

    public StringProperty getSelectedIdentificationLabel() {
        return selectedIdentificationLabel;
    }

    public StringProperty selectedIdentificationLabelProperty() {
        return selectedIdentificationLabel;
    }

    public StringProperty getSelectedCaptainField() {
        return selectedCaptainField;
    }

    public StringProperty selectedCaptainFieldProperty() {
        return selectedCaptainField;
    }

    public StringProperty getSelectedCaptainLabel() {
        return selectedCaptainLabel;
    }

    public StringProperty selectedCaptainLabelProperty() {
        return selectedCaptainLabel;
    }

    public StringProperty getSelectedCommissioningField() {
        return selectedCommissioningField;
    }

    public StringProperty selectedCommissioningFieldProperty() {
        return selectedCommissioningField;
    }

    public StringProperty getSelectedCommissioningLabel() {
        return selectedCommissioningLabel;
    }

    public StringProperty selectedCommissioningLabelProperty() {
        return selectedCommissioningLabel;
    }

    public StringProperty getSelectedAvailableField() {
        return selectedAvailableField;
    }

    public StringProperty selectedAvailableFieldProperty() {
        return selectedAvailableField;
    }

    public StringProperty getSelectedAvailableLabel() {
        return selectedAvailableLabel;
    }

    public StringProperty selectedAvailableLabelProperty() {
        return selectedAvailableLabel;
    }

    public StringProperty getSelectedTypeField() {
        return selectedTypeField;
    }

    public StringProperty selectedTypeFieldProperty() {
        return selectedTypeField;
    }

    public StringProperty getSelectedTypeLabel() {
        return selectedTypeLabel;
    }

    public StringProperty selectedTypeLabelProperty() {
        return selectedTypeLabel;
    }
}