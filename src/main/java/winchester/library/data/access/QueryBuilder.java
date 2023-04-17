package winchester.library.data.access;

import winchester.library.data.model.items.Item;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.Employee;

public class QueryBuilder {

    private String tables;
    private String columns;
    private String conditions;
    private String values;
    private final SQLQueryType type;

    private QueryBuilder(SQLQueryType type) {
        this.type = type;
    }

    public static QueryBuilder createQuery(SQLQueryType type) {
        return new QueryBuilder(type);
    }

    public QueryBuilder select(String... columns) {
        this.columns = this.convertToSingleString(columns, ", ");
        return this;
    }

    public QueryBuilder from(String... tables) {
        this.tables = this.convertToSingleString(tables, ", ");
        return this;
    }

    public QueryBuilder where(String... conditions) {
        this.conditions = this.convertToSingleString(conditions, "  ");
        return this;
    }

    public QueryBuilder insertInto(String table) {
        this.tables = table;
        return this;
    }

    public QueryBuilder values(Employee... employees) {
        this.values = convertToSingleString(employees);
        return this;
    }

    public QueryBuilder values(Item... items) {
        this.values = convertToSingleString(items);
        return this;
    }

    public QueryBuilder values(Loan... loans) {
        this.values = convertToSingleString(loans);
        return this;
    }
    
    private String convertToSingleString(String[] tokens, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String token : tokens) {
            stringBuilder.append(token).append(separator);
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    private String convertToSingleString(Employee[] employees) {
        return null;
    }

    private String convertToSingleString(Item[] items) {
        return null;
    }

    private String convertToSingleString(Loan[] loans) {
        return null;
    }

    @Override
    public String toString() {
        return switch (this.type) {
            case GET -> String.format(
                    this.type.getQueryFormat(), this.columns, this.tables);
            case GET_AND_FILTER -> String.format(
                    this.type.getQueryFormat(), this.columns, this.tables, this.conditions);
            default -> null;
        };
    }

}