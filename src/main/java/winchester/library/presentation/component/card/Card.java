package winchester.library.presentation.component.card;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import winchester.library.presentation.style.ComponentStyler;

/**
 * A base card class that provides common components, layouts and styling.
 */
public abstract class Card extends BorderPane {

    protected VBox actions;
    protected Label viewDetailsLabel;

    /**
     * The default constructor that base styles the component.
     */
    public Card() {
        this.getStyleClass().add("background-secondary-border");
        this.setPadding(new Insets(5));
        this.loadStylesheets();
        this.initialiseAndAddDefaultComponents();
    }

    /**
     * Loads and sets the stylesheet for this component.
     */
    private void loadStylesheets() {
        ComponentStyler.getInstance().setStyle(this);
    }

    /**
     * Initialises some default components that will be used across the inherited classes.
     */
    private void initialiseAndAddDefaultComponents() {
        this.actions = new VBox();
        this.actions.setPadding(new Insets(10));
        this.viewDetailsLabel = new Label();
        this.viewDetailsLabel.getStyleClass().add("link-label");
        this.viewDetailsLabel.setText("View Details");
        this.actions.getChildren().add(this.viewDetailsLabel);
        this.setRight(this.actions);
    }
}
