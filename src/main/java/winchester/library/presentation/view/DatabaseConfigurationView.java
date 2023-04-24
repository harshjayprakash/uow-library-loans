package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import winchester.library.data.access.DatabaseConnectionTester;
import winchester.library.data.access.DatabaseConstant;
import winchester.library.data.access.DatabaseCredentials;
import winchester.library.presentation.window.WindowBase;

public class DatabaseConfigurationView extends View {

    private final WindowBase parentWindow;
    private final DatabaseCredentials credentials;
    private HBox buttonLayout;
    private Label descriptionLabel;
    private Label urlLabel;
    private Label usernameLabel;
    private Label passwordLabel;
    private TextField urlField;
    private TextField usernameField;
    private TextField passwordField;
    private Button cancelButton;
    private Button saveAndTestButton;

    public DatabaseConfigurationView(WindowBase parentWindow) {
        super(parentWindow, Views.DATABASE_CONFIGURATION.toString());
        this.parentWindow = parentWindow;
        this.parentWindow.setTitleText(Views.DATABASE_CONFIGURATION.toString());
        this.parentWindow.setWidth(550);
        this.parentWindow.setHeight(375);
        this.credentials = DatabaseCredentials.getInstance();
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    @Override
    protected void initialiseLayouts() {
        this.buttonLayout = new HBox();
        this.buttonLayout.setAlignment(Pos.CENTER_RIGHT);
        this.buttonLayout.setPadding(new Insets(15, 0, 0, 0));
    }

    @Override
    protected void initialiseControls() {
        this.descriptionLabel = new Label();
        this.descriptionLabel.setText(
            "Setting database connection information can stop the program from functioning."
            + " This view is for advanced users only.");
        this.descriptionLabel.setWrapText(true);
        this.urlLabel = new Label();
        this.urlLabel.setPadding(new Insets(10, 0, 0, 0));
        this.urlLabel.setText("Database URL: ");
        this.urlField = new TextField();
        this.urlField.setText(this.credentials.getUrl());
        this.usernameLabel = new Label();
        this.usernameLabel.setPadding(new Insets(10, 0, 0 , 0));
        this.usernameLabel.setText("Database Username: ");
        this.usernameField = new TextField();
        this.usernameField.setText(this.credentials.getUsername());
        this.passwordLabel = new Label();
        this.passwordLabel.setPadding(new Insets(10, 0, 0, 0));
        this.passwordLabel.setText("Database Password: ");
        this.passwordField = new TextField();
        this.passwordField.setText(this.credentials.getPassword());
        this.cancelButton = new Button();
        this.cancelButton.setText("Cancel");
        this.saveAndTestButton = new Button();
        this.saveAndTestButton.setText("Test Connection and Save");
        HBox.setMargin(this.saveAndTestButton, new Insets(0, 0, 0, 10));
    }

    private void bindEventHandlers() {
        this.cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.parentWindow.close());
        this.saveAndTestButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            DatabaseConstant testResult = DatabaseConnectionTester.getInstance().testCredentials(this.credentials);
            if (testResult != DatabaseConstant.CONNECTION_SUCCESSFUL) {
                return;
            }
            this.credentials.setUrl(this.urlField.getText());
            this.credentials.setUsername(this.usernameField.getText());
            this.credentials.setPassword(this.passwordField.getText());
            this.parentWindow.close();
        });
    }

    @Override
    protected void addComponentsToView() {
        this.buttonLayout.getChildren().addAll(this.cancelButton, this.saveAndTestButton);
        this.getChildren().addAll(
            this.descriptionLabel, this.urlLabel, this.urlField, this.usernameLabel, this.usernameField, 
            this.passwordLabel, this.passwordField, this.buttonLayout);
    }

}
