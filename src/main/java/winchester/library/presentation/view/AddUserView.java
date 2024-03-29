package winchester.library.presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import winchester.library.presentation.alert.AlertFactory;
import winchester.library.presentation.window.WindowBase;
import winchester.library.service.DataPersistenceManager;

/**
 * A view that allows the adding of customers to the data source.
 */
public final class AddUserView extends View {

    private Label descriptionLabel;
    private Label firstNameLabel;
    private Label lastNameLabel;
    private Label postalCodeLabel;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField postalCodeField;
    private HBox actionButtonsLayout;
    private Button cancelButton;
    private Button createButton;

    /**
     * The default constructor that passes the parent window.
     * @param parentWindow the parent window that the view can access.
     */
    public AddUserView(WindowBase parentWindow) {
        super(parentWindow, Views.ADD_USER.toString());
        this.parentWindow.setWidth(400);
        this.parentWindow.setHeight(350);
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToView();
    }

    /**
     * A method to initialise any layouts used within the view.
     */
    private void initialiseLayouts() {
        this.actionButtonsLayout = new HBox();
        this.actionButtonsLayout.setAlignment(Pos.CENTER_RIGHT);
        this.actionButtonsLayout.setSpacing(10);
        this.actionButtonsLayout.setPadding(new Insets(10, 0, 0, 0));
    }

    /**
     * A method to initialise any controls used within the view.
     */
    private void initialiseControls() {
        this.descriptionLabel = new Label();
        this.descriptionLabel.setText("Please enter the details of the new customer.");
        this.descriptionLabel.setPadding(new Insets(0, 0, 10, 0));
        this.firstNameLabel = new Label();
        this.firstNameLabel.setText("First Name: ");
        this.firstNameField = new TextField();
        this.lastNameLabel = new Label();
        this.lastNameLabel.setText("Last Name: ");
        this.lastNameLabel.setPadding(new Insets(10, 0, 0, 0));
        this.lastNameField = new TextField();
        this.postalCodeLabel = new Label();
        this.postalCodeLabel.setText("Postal Code: ");
        this.postalCodeLabel.setPadding(new Insets(10, 0, 0, 0));
        this.postalCodeField = new TextField();
        this.cancelButton = new Button();
        this.cancelButton.setText("Cancel");
        this.createButton = new Button();
        this.createButton.setText("Create");
        this.createButton.getStyleClass().add("button-accent");
    }

    /**
     * A method to bind and add event handlers to components.
     */
    private void bindEventHandlers() {
        this.cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.parentWindow.close());
        this.createButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.createCustomer());
    }

    /**
     * A method to add components to the view.
     */
    private void addComponentsToView() {
        this.actionButtonsLayout.getChildren().addAll(this.cancelButton, this.createButton);
        this.getChildren().addAll(this.descriptionLabel, this.firstNameLabel, this.firstNameField, this.lastNameLabel,
                this.lastNameField, this.postalCodeLabel, this.postalCodeField, this.actionButtonsLayout);
    }

    /**
     * A method to create a customer and add it to the data source.
     */
    private void createCustomer() {
        if (this.firstNameField.getText().isBlank() && this.lastNameField.getText().isBlank()
                && this.postalCodeField.getText().isBlank()) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Form is not complete.",
                    "Please ensure that all fields have been filled out.").show();
            return;
        }
        boolean success = DataPersistenceManager.getInstance().createCustomer(
                this.firstNameField.getText(), this.lastNameField.getText(), this.postalCodeField.getText());
        if (!success) {
            AlertFactory.createAlert(Alert.AlertType.ERROR, "Failed to create new customer.").show();
            return;
        }
        AlertFactory.createAlert(Alert.AlertType.INFORMATION, "Successfully created new customer.").show();
        this.parentWindow.close();
    }
}
