package winchester.library.presentation.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import winchester.library.presentation.style.ComponentStyler;

/**
 * An abstract factory class that abstracts the creation of alert message boxes.
 */
public abstract class AlertFactory {

    /**
     * A static factory method to create an alert message box with header and content text.
     * @param type the type of alert to be displayed.
     * @param headerText the header text to be displayed.
     * @param contentText the content text to be displayed.
     * @return a new instance of an alert.
     */
    public static Alert createAlert(Alert.AlertType type, String headerText, String contentText) {
        Alert alert = new Alert(type);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        ComponentStyler.getInstance().setStyle(alert.getDialogPane());
        return alert;
    }

    /**
     * A static factory method to create an alert message box with the header text.
     * @param type the type of alert to be displayed.
     * @param headerText the header text to be displayed.
     * @return a new instance of an alert.
     */
    public static Alert createAlert(Alert.AlertType type, String headerText) {
        Alert alert = new Alert(type);
        alert.setHeaderText(headerText);
        ComponentStyler.getInstance().setStyle(alert.getDialogPane());
        return alert;
    }

    /**
     * A static factory method to create an alert message with header and context text with additional buttons.
     * @param type the type of alert to be displayed.
     * @param headerText the header text to be displayed.
     * @param contentText the content text to be displayed.
     * @param buttonTypes the button types to be displayed.
     * @return a new instance of an alert.
     */
    public static Alert createAlert(Alert.AlertType type, String headerText, String contentText,
                                    ButtonType... buttonTypes) {
        Alert alert = new Alert(type, contentText, buttonTypes);
        alert.setHeaderText(headerText);
        ComponentStyler.getInstance().setStyle(alert.getDialogPane());
        return alert;
    }

}
