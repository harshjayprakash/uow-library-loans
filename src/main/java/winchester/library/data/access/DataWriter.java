package winchester.library.data.access;

import java.util.Optional;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.Customer;
import winchester.library.data.model.users.Employee;

/**
 * A class that abstracts the method of creating a new record within the database.
 */
public class DataWriter {

    private final DatabaseCredentials credentials;

    /**
     * The default constructor that gets the DatabaseCredentials instance.
     */
    public DataWriter() {
        this.credentials = DatabaseCredentials.getInstance();
    }

    /**
     * A method to insert an employee into the data source.
     * @param employee the employee to be inserted.
     * @return a DatabaseConstant enumeration of either INSERTION_ERROR if the operation failed or if the operation
     * succeeded returns it returns INSERTION_SUCCESSFUL.
     */
    public DatabaseConstant insert(Employee employee) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> userInsertResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.INSERT_ONE)
                        .insertInto("library.users")
                        .values(String.format("(%d, %d, '%s', '%s', '%s')",
                                employee.getIdentifier(), employee.getType().getIdentifier(), employee.getFirstName(),
                                employee.getLastName(), employee.getPostalCode())));
        if (userInsertResult.isEmpty()) {
            return DatabaseConstant.INSERTION_ERROR;
        }
        Optional<Integer> employeeInsertResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.INSERT_ONE)
                        .insertInto("library.employees (user_id, username, password, status_id)")
                        .values(String.format("(%d, '%s', '%s', %d)",
                                employee.getIdentifier(), employee.getUsername(), employee.getHashedPassword(),
                                employee.getStatus().getIdentifier()))
        );
        if (employeeInsertResult.isEmpty()) {
            return DatabaseConstant.INSERTION_ERROR;
        }
        connection.close();
        return DatabaseConstant.INSERTION_SUCCESSFUL;
    }

    /**
     * A method to insert a customer into the data source.
     * @param customer the customer to be inserted.
     * @return a DatabaseConstant enumeration of either INSERTION_ERROR if the operation failed or if the operation
     * succeeded returns it returns INSERTION_SUCCESSFUL.
     */
    public DatabaseConstant insert(Customer customer) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> userInsertResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.INSERT_ONE)
                        .insertInto("library.users")
                        .values(String.format("(%d, %d, '%s', '%s', '%s')",
                                customer.getIdentifier(), customer.getType().getIdentifier(), customer.getFirstName(),
                                customer.getLastName(), customer.getPostalCode())));
        if (userInsertResult.isEmpty()) {
            return DatabaseConstant.INSERTION_ERROR;
        }
        connection.close();
        return DatabaseConstant.INSERTION_SUCCESSFUL;
    }

    /**
     * A method to insert a loan into the data source.
     * @param loan the loan to be inserted.
     * @return a DatabaseConstant enumeration of either INSERTION_ERROR if the operation failed or if the operation
     * succeeded returns it returns INSERTION_SUCCESSFUL.
     */
    public DatabaseConstant insert(Loan loan) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> loanInsertResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.INSERT_ONE)
                        .insertInto("library.loans (customer_id, item_id, item_subtype_id, loan_date, return_date, returned)")
                        .values(String.format("(%d,'%s', %d, '%s', '%s', %d)",
                                loan.getCustomer().getIdentifier(), loan.getLoanedItemIdentifier(),
                                loan.getLoanedItemFormat().getIdentifier(), loan.getLoanDate().toString(),
                                loan.getDueDate().toString(), (loan.getReturned()) ? 1 : 0)));
        if (loanInsertResult.isEmpty()) {
            return DatabaseConstant.INSERTION_ERROR;
        }
        connection.close();
        return DatabaseConstant.INSERTION_SUCCESSFUL;
    }

}
