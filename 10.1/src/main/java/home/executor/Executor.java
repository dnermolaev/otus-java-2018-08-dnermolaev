package home.executor;

import home.handler.TResultHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;


public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    /*public void execQuery(String query, ResultHandler handler) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            handler.handle(result);
        }
    }*/

    public <T> T execQuery(String query, TResultHandler<T> handler) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try(Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            return handler.handle(result);
        }
    }

    public void execUpdate(String update, ExecuteHandler prepare) throws IllegalAccessException {
        try{
        PreparedStatement stmt = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
        prepare.accept(stmt);
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }}

    public int execUpdate(String update) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(update);
            return stmt.getUpdateCount();
        }
    }

    Connection getConnection() {
        return connection;
    }

    @FunctionalInterface
    public interface ExecuteHandler {
    void accept(PreparedStatement statement) throws SQLException, IllegalAccessException;
    }
}
