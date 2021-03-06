package home.executor;

import home.DBServiceException;
import home.handler.TResultHandler;
import java.sql.*;


public class Executor {
    private final Connection connection;

    public Executor(Connection connection) throws DBServiceException {
        this.connection = connection;
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            throw new DBServiceException(e.toString());
        }
    }

    /*public void execQuery(String query, ResultHandler handler) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            handler.handle(result);
        }
    }*/

    public <T> T execQuery(String query, TResultHandler<T> handler) throws DBServiceException {
        try(Statement stmt = connection.createStatement()) {

            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            return handler.handle(result);
        } catch (SQLException e) {
            throw new DBServiceException(e.toString());
        }
    }

    public void execUpdate(String update, ExecuteHandler prepare) throws DBServiceException {
        try{

            PreparedStatement stmt = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            prepare.accept(stmt);
            stmt.close();
        } catch (SQLException e) {
            throw new DBServiceException(e.toString());
    }}

    public int execUpdate(String update) throws DBServiceException {
        try (Statement stmt = connection.createStatement()) {

            stmt.execute(update);
            return stmt.getUpdateCount();
        } catch (SQLException e) {
            throw new DBServiceException(e.toString());
        }
    }

    Connection getConnection() {
        return connection;
    }

    @FunctionalInterface
    public interface ExecuteHandler {
    void accept(PreparedStatement statement) throws DBServiceException;
    }
}
