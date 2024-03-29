package winchester.library.presentation.view;

import javafx.scene.layout.BorderPane;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.Customer;
import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.User;
import winchester.library.presentation.window.WindowBase;

/**
 * A class that provides an abstraction allowing the switching between views.
 */
public final class ViewsManager extends BorderPane {

    /**
     * The default constructor.
     */
    public ViewsManager() {
        super();
    }

    /**
     * A method to specify the view to be shown.
     * @param view the view to be shown
     * @param parentWindow the parent window that view can have access to.
     * @param user the user that view can have access to.
     * @param item the item that view can have access to.
     * @param loan the loan that view can have access to.
     */
    public void showView(Views view, WindowBase parentWindow, User user, Item item, Loan loan) {
        this.setCenter(null);
        this.setCenter(
                switch (view) {
                    case HOME -> new HomeView(parentWindow, Employee.castFrom(user));
                    case LOANS -> new LoansView(parentWindow);
                    case LOGIN -> new LoginView(parentWindow);
                    case USERS -> new UsersView(parentWindow);
                    case SETTINGS -> new SettingsView(parentWindow);
                    case ADD_ITEM -> new AddItemView(parentWindow);
                    case REGISTER -> new RegisterView(parentWindow);
                    case ADD_USER -> new AddUserView(parentWindow);
                    case CUSTOMERS -> new CustomerView(parentWindow);
                    case INVENTORY -> new InventoryView(parentWindow);
                    case LOANING_ITEMS -> new LoaningItemView(parentWindow, Customer.castFrom(user));
                    case CHANGE_PASSWORD -> new ChangePasswordView(parentWindow, Employee.castFrom(user));
                    case INDIVIDUAL_ITEM -> new IndividualItemView(parentWindow, item);
                    case INDIVIDUAL_LOAN -> new IndividualLoanView(parentWindow, loan);
                    case NONE_WITH_SIDEBAR -> new NoneSidePaneView(parentWindow);
                    case INDIVIDUAL_CUSTOMER -> new IndividualCustomerView(parentWindow, Customer.castFrom(user));
                    case INDIVIDUAL_EMPLOYEE -> new IndividualEmployeeView(parentWindow, Employee.castFrom(user));
                    case DATABASE_CONFIGURATION -> new DatabaseConfigurationView(parentWindow);
                }
        );
    }


}
