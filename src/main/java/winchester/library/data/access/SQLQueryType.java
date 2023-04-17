package winchester.library.data.access;

public enum SQLQueryType {
    GET("SELECT %s FROM %s;"),
    GET_AND_FILTER("SELECT %s FROM %s WHERE %s;"),
    INSERT_ONE("INSERT INTO %s VALUE %s;"),
    INSERT_MANY("INSERT INTO %s VALUES %s;"),
    UPDATE("UPDATE %s SET %s;"),
    UPDATE_AND_FILTER("UPDATE %s SET %s WHERE %s;");

    private final String queryFormat;

    SQLQueryType(String queryFormat) {
        this.queryFormat = queryFormat;
    }

    public String getQueryFormat() {
        return this.queryFormat;
    }
}