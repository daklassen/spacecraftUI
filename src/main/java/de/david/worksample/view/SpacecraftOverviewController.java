package de.david.worksample.view;

import de.david.worksample.MainApplication;
import de.david.worksample.service.SpacecraftTO;
import de.david.worksample.model.Harbor;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.util.Optional;

public class SpacecraftOverviewController {
    @FXML
    private ListView<SpacecraftTO> spacecraftList;
    @FXML
    private Label identificationField;
    @FXML
    private Label identificationLabel;
    @FXML
    private Label captainField;
    @FXML
    private Label captainLabel;
    @FXML
    private Label commissioningField;
    @FXML
    private Label commissioningLabel;
    @FXML
    private Label availableField;
    @FXML
    private Label availableLabel;
    @FXML
    private Label typeField;
    @FXML
    private Label typeLabel;

    @FXML
    private TextField filterTextField;

    @FXML
    private Label notFoundField;
    @FXML
    private ImageView notFoundImage;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;

    private MainApplication mainApp;

    public SpacecraftOverviewController() {
    }

    public void setMainApp(MainApplication mainapp) {
        this.mainApp = mainapp;
    }

    public void initialize() {
        identificationField.textProperty().bind(Harbor.getInstance().getSelectedIdentificationField());
        identificationLabel.textProperty().bind(Harbor.getInstance().getSelectedIdentificationLabel());
        captainField.textProperty().bind(Harbor.getInstance().getSelectedCaptainField());
        captainLabel.textProperty().bind(Harbor.getInstance().getSelectedCaptainLabel());
        commissioningField.textProperty().bind(Harbor.getInstance().getSelectedCommissioningField());
        commissioningLabel.textProperty().bind(Harbor.getInstance().getSelectedCommissioningLabel());
        availableField.textProperty().bind(Harbor.getInstance().getSelectedAvailableField());
        availableLabel.textProperty().bind(Harbor.getInstance().getSelectedAvailableLabel());
        typeField.textProperty().bind(Harbor.getInstance().getSelectedTypeField());
        typeLabel.textProperty().bind(Harbor.getInstance().getSelectedTypeLabel());

        toggleSelected(false);

        spacecraftList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Harbor.getInstance().setSelection(newValue);
                    toggleSelected(true);
                });

        spacecraftList.setCellFactory(new Callback<ListView<SpacecraftTO>, ListCell<SpacecraftTO>>() {
            @Override
            public ListCell<SpacecraftTO> call(ListView<SpacecraftTO> param) {
                return new CustomListCell();
            }
        });

        FilteredList<SpacecraftTO> filteredSpacecrafts =
                new FilteredList<SpacecraftTO>(Harbor.getInstance().getObservableSpacecraftList());

        filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredSpacecrafts.setPredicate(spacecraft -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (spacecraft.identification.toLowerCase().contains(lowerCaseFilter) ||
                        spacecraft.captain.toLowerCase().contains(lowerCaseFilter) ||
                        spacecraft.type.toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        spacecraftList.setItems(filteredSpacecrafts);
    }

    private void toggleSelected(boolean isSelected) {
        if (isSelected) {
            notFoundField.setText("");
            notFoundImage.setOpacity(0);
            deleteButton.setDisable(false);
            editButton.setDisable(false);
        } else {
            notFoundField.setText("No spacecraft selected");
            notFoundImage.setOpacity(.2);
            deleteButton.setDisable(true);
            editButton.setDisable(true);
        }
    }

    @FXML
    private void handleEditSpacecraft() {
        try {
            if (spacecraftList.getSelectionModel().getSelectedItem() != null) {
                SpacecraftTO selectedSpacecraft = Harbor.getInstance().getSelectedSpacecraft();
                mainApp.showEditSpacecraftView();
                Harbor.getInstance().setSelection(selectedSpacecraft);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteSpacecraft() {
        try {
            SpacecraftTO selectedSpacecraft = spacecraftList.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("You are about to delete Spacecraft \"" + selectedSpacecraft.identification + "\"");
            alert.initStyle(StageStyle.UTILITY);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Harbor.getInstance().deleteSpacecraft(selectedSpacecraft.id);
            }
            if (Harbor.getInstance().getSpacecrafts().isEmpty()) {
                toggleSelected(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddSpacecraft() {
        mainApp.showCreateSpacecraftView();
    }
}