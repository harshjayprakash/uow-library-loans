package winchester.library.presentation.component.card;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.items.ItemType;
import winchester.library.data.model.loans.Loan;
import winchester.library.presentation.view.Views;
import winchester.library.presentation.window.IndividualViewWindow;
import winchester.library.service.Searcher;

/**
 * A class that provides a control to view loan information.
 */
public final class LoanCard extends Card {

    private VBox loanInformation;
    private Label loanIdentifierLabel;
    private Label customerNameLabel;
    private Label itemTitleLabel;
    private Label itemFormatLabel;
    private Label itemReturnDueLabel;
    private final Loan referencedLoan;

    /**
     * The default constructor to create the LoanCard component.
     * @param loan the loan that will be referenced.
     */
    public LoanCard(Loan loan) {
        super();
        this.referencedLoan = loan;
        this.initialiseLayouts();
        this.initialiseControls();
        this.bindEventHandlers();
        this.addComponentsToCard();
    }

    /**
     * A method to initialise the layouts that will be used within the card component.
     */
    private void initialiseLayouts() {
        this.loanInformation = new VBox();
        this.loanInformation.getStyleClass().add("background-secondary");
        this.loanInformation.setPadding(new Insets(10));
    }

    /**
     * A method to initialise the controls that will be shown within the card component.
     */
    private void initialiseControls() {
        this.loanIdentifierLabel = new Label();
        this.loanIdentifierLabel.getStyleClass().add("text-bold");
        this.loanIdentifierLabel.setText(String.valueOf(this.referencedLoan.getIdentifier()));
        this.itemTitleLabel = new Label();
        Item referencedItem = new Searcher().searchItemFromIdentifier(this.referencedLoan.getLoanedItemIdentifier());
        if (referencedItem.getType() == ItemType.BOOK) {
            this.itemTitleLabel.setText(String.format("Title : %s", Book.castFrom(referencedItem).getTitle()));
        }
        else if (referencedItem.getType() == ItemType.FILM) {
            this.itemTitleLabel.setText(String.format("Title : %s", Film.castFrom(referencedItem).getTitle()));
        }
        this.itemFormatLabel = new Label();
        this.itemFormatLabel.setText(
                String.format("Format : %s", this.referencedLoan.getLoanedItemFormat().toString()));
        this.itemReturnDueLabel = new Label();
        this.itemReturnDueLabel.setText(String.format("Due Date : %s", this.referencedLoan.getDueDate().toString()));
        this.customerNameLabel = new Label();
        this.customerNameLabel.setText(
                String.format("Customer : %s", this.referencedLoan.getCustomer().getFullName()));
    }

    /**
     * A method to add event handlers to any controls.
     */
    private void bindEventHandlers() {
        this.viewDetailsLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.startIndividualItemWindow());
    }

    /**
     * A method to add any layouts and controls initialised to the card.
     */
    private void addComponentsToCard() {
        this.loanInformation.getChildren().addAll(this.loanIdentifierLabel, this.customerNameLabel, this.itemTitleLabel,
                this.itemFormatLabel, this.itemReturnDueLabel);
        this.setCenter(this.loanInformation);
    }

    /**
     * A method to start a window with the individual item view.
     */
    private void startIndividualItemWindow() {
        IndividualViewWindow individualItemView = new IndividualViewWindow(Views.INDIVIDUAL_LOAN, this.referencedLoan);
        individualItemView.show();
    }
}