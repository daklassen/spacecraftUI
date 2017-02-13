package de.david.worksample.view;

import de.david.worksample.model.Harbor;
import de.david.worksample.model.SpacecraftType;
import de.david.worksample.service.SpacecraftServiceException;
import de.david.worksample.service.SpacecraftTO;
import de.david.worksample.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ChangeSpacecraftDialogController {
    @FXML
    private TextField identificationTextField;
    @FXML
    private TextField captainTextField;
    @FXML
    private ComboBox<SpacecraftType> typeComboBox;
    @FXML
    private DatePicker commissioningDatePicker;
    @FXML
    private RadioButton availableCheckBox;
    @FXML
    private Label headlineLabel;
    @FXML
    private Button changeButton;
    @FXML
    private Label idLabel;

    private Stage dialogStage;

    @FXML
    private void initialize() {
        typeComboBox.getItems().setAll(SpacecraftType.values());
        commissioningDatePicker.setShowWeekNumbers(false);
    }

    public void fillDialog() {
        headlineLabel.setText("Edit Spacecraft");
        changeButton.setText("Save");

        identificationTextField.setText(Harbor.getInstance().getSelectedIdentificationField().getValue());
        captainTextField.setText(Harbor.getInstance().getSelectedCaptainField().getValue());
        typeComboBox.getItems().setAll(SpacecraftType.values());
        typeComboBox.getSelectionModel().select(Harbor.getInstance().getSelectedSpacecraft().type);
        commissioningDatePicker.setShowWeekNumbers(false);
        commissioningDatePicker.setValue(DateUtil.dateTolocalDate(Harbor.getInstance().getSelectedSpacecraft().commissioning));
        availableCheckBox.setSelected(Harbor.getInstance().getSelectedSpacecraft().available);
        idLabel.setText(new Long(Harbor.getInstance().getSelectedSpacecraft().id).toString());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleChange() {
        if (idLabel.getText().equals("")) {
            handleCreate();
        } else {
            handleSave();
        }
    }

    private void handleCreate() {
        if (isInputValid()) {
            //create new Spaceship with user input
            SpacecraftTO spacecraft = new SpacecraftTO();
            spacecraft.identification = identificationTextField.getText().trim();
            spacecraft.captain = captainTextField.getText().trim();
            spacecraft.type = typeComboBox.getSelectionModel().getSelectedItem();
            spacecraft.commissioning = DateUtil.localDateToDate(commissioningDatePicker.getValue());
            spacecraft.available = availableCheckBox.isSelected();

            Harbor.getInstance().createSpacecraft(spacecraft);

            dialogStage.close();
        }
    }

    private void handleSave() {
        if (isInputValid()) {
            SpacecraftTO editedSpacecraft = new SpacecraftTO();
            editedSpacecraft.identification = identificationTextField.getText().trim();
            editedSpacecraft.captain = captainTextField.getText().trim();
            editedSpacecraft.type = typeComboBox.getSelectionModel().getSelectedItem();
            editedSpacecraft.commissioning = DateUtil.localDateToDate(commissioningDatePicker.getValue());
            editedSpacecraft.available = availableCheckBox.isSelected();
            editedSpacecraft.id = Long.valueOf(idLabel.getText());

            try {
                Harbor.getInstance().setSpacecraft(editedSpacecraft);
            } catch (SpacecraftServiceException e) {
                e.printStackTrace();
            }

            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (identificationTextField.getText() == null || identificationTextField.getText().trim().isEmpty()) {
            errorMessage += "No valid identification for spacecraft!\n";
        }

        if (captainTextField.getText() == null || captainTextField.getText().trim().isEmpty()) {
            errorMessage += "No valid name for captain!\n";
        }

        if (commissioningDatePicker.getValue() == null) {
            errorMessage += "No commissioning date given!\n";
        }

        if (typeComboBox.getValue() == null) {
            errorMessage += "No spacecraft-type given!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }
}
