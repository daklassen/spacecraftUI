package de.david.worksample;

import de.david.worksample.model.Harbor;
import de.david.worksample.model.SpacecraftType;
import de.david.worksample.service.SpacecraftTO;
import de.david.worksample.view.ChangeSpacecraftDialogController;
import de.david.worksample.view.SpacecraftOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class MainApplication extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    public MainApplication() {
        // add test data
        SpacecraftTO spacecraft1 = new SpacecraftTO("SA-23E Aurora", "James Tiberius Kirk", new Date(), true, SpacecraftType.CRUISER);
        SpacecraftTO spacecraft2 = new SpacecraftTO("Raider Fighter", "Colonel Jack O'Neil", new Date(), true, SpacecraftType.FRIGATE);
        SpacecraftTO spacecraft3 = new SpacecraftTO("Colonial Viper", "David Bowman", new Date(), true, SpacecraftType.FREIGHTER);
        SpacecraftTO spacecraft4 = new SpacecraftTO("Star Fighter", "William T. Riker", new Date(), false, SpacecraftType.FERRY);
        SpacecraftTO spacecraft5 = new SpacecraftTO("ARC-170 Starfighter", "James Tiberius Kirk", new Date(), true, SpacecraftType.CRUISER);
        SpacecraftTO spacecraft6 = new SpacecraftTO("Raider Fighter", "Colonel Jack O'Neil", new Date(), false, SpacecraftType.FREIGHTER);
        SpacecraftTO spacecraft7 = new SpacecraftTO("V-Wing", "David Bowman", new Date(), true, SpacecraftType.CRUISER);
        SpacecraftTO spacecraft8 = new SpacecraftTO("Raider", "William T. Riker", new Date(), true, SpacecraftType.FERRY);
        SpacecraftTO spacecraft9 = new SpacecraftTO("Droid Tri-Fighter", "William T. Riker", new Date(), true, SpacecraftType.FERRY);
        SpacecraftTO spacecraft10 = new SpacecraftTO("SA-23E Aurora", "James Tiberius Kirk", new Date(), false, SpacecraftType.FRIGATE);
        SpacecraftTO spacecraft11 = new SpacecraftTO("Naboo starfighter", "James Bond", new Date(), true, SpacecraftType.FERRY);

        Harbor.getInstance().createSpacecraft(spacecraft1);
        Harbor.getInstance().createSpacecraft(spacecraft2);
        Harbor.getInstance().createSpacecraft(spacecraft3);
        Harbor.getInstance().createSpacecraft(spacecraft4);
        Harbor.getInstance().createSpacecraft(spacecraft5);
        Harbor.getInstance().createSpacecraft(spacecraft6);
        Harbor.getInstance().createSpacecraft(spacecraft7);
        Harbor.getInstance().createSpacecraft(spacecraft8);
        Harbor.getInstance().createSpacecraft(spacecraft9);
        Harbor.getInstance().createSpacecraft(spacecraft10);
        Harbor.getInstance().createSpacecraft(spacecraft11);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SpacecraftApp");

        // Font antialiasing
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");

        // App icon
        this.primaryStage.getIcons().add(new Image("images/icon.png"));

        initRootLayout();

        showSpacecraftOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the spacecraft overview (list + details) layout.
     */
    public void showSpacecraftOverview() {
        try {
            // Load spacecraft overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/SpacecraftOverview.fxml"));
            AnchorPane spacecraftOverview = (AnchorPane) loader.load();

            SpacecraftOverviewController controller = loader.getController();
            controller.setMainApp(this);

            // Set spacecraft overview into the center of root layout.
            rootLayout.setCenter(spacecraftOverview);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the create spacecraft layout..
     */
    public void showCreateSpacecraftView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("/fxml/ChangeSpacecraftDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Create Spacecraft");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ChangeSpacecraftDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the edit spacecraft layout.
     */
    public void showEditSpacecraftView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("/fxml/ChangeSpacecraftDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Spacecraft");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ChangeSpacecraftDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.fillDialog();

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
