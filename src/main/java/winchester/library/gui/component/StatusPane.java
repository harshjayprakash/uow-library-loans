package winchester.library.gui.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import winchester.library.gui.style.StylesheetSetter;

public class StatusPane extends BorderPane {
    private Label versionNumberLabel;
    private Label databaseStatusLabel;

    public StatusPane() {
        super();
        this.setPadding(new Insets(5));
        this.initialiseControls();
        this.loadStylesheets();
        this.bindEventHandlers();
        this.addComponentsToPane();
    }

    private void initialiseControls() {
        this.versionNumberLabel = new Label();
        this.versionNumberLabel.setPadding(new Insets(5));
        this.versionNumberLabel.setText("Version 1.2");
        this.databaseStatusLabel = new Label();
        this.databaseStatusLabel.setAlignment(Pos.CENTER_RIGHT);
        this.databaseStatusLabel.setId("status-pane__database-status-label");
        this.databaseStatusLabel.setPadding(new Insets(5));
        this.databaseStatusLabel.setText("Database not connected");
        this.databaseStatusLabel.setTextAlignment(TextAlignment.RIGHT);
    }

    private void loadStylesheets() {
        StylesheetSetter.getInstance().setStyle(this);
    }

    private void bindEventHandlers() {

    }

    private void addComponentsToPane() {
        this.setLeft(this.versionNumberLabel);
        this.setRight(this.databaseStatusLabel);
    }
}