package de.david.worksample.view;

import de.david.worksample.service.SpacecraftTO;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class CustomListCell extends ListCell<SpacecraftTO> {
    HBox hbox = new HBox();
    Label label = new Label("(empty)");
    Pane pane = new Pane();
    String lastItem;

    public CustomListCell() {
        super();
        hbox.setFillHeight(true);
        hbox.setAlignment(Pos.CENTER_LEFT);

        label.getStyleClass().add("list-cell-2");

        hbox.getChildren().addAll(label);
        HBox.setHgrow(pane, Priority.ALWAYS);
    }

    @Override
    protected void updateItem(SpacecraftTO item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            lastItem = null;
            setGraphic(null);
            setText("");
        } else {
            label.setText(item != null ? item.identification : "<null>");
            setGraphic(hbox);
        }
    }

}
