package home.connection;

import home.base.DBService;
import home.base.DataSet;
import home.base.UsersDataSet;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBServiceConnection implements DBService {

    private final Connection connection;

    public DBServiceConnection() {
        connection = ConnectionHelper.getConnection();
    }

    @Override
    public String getMetaData() {
        try {
            return "Connected to: " + getConnection().getMetaData().getURL() + "\n" +
                    "DB name: " + getConnection().getMetaData().getDatabaseProductName() + "\n" +
                    "DB version: " + getConnection().getMetaData().getDatabaseProductVersion() + "\n" +
                    "Driver: " + getConnection().getMetaData().getDriverName();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public void prepareTables(String tableName) throws SQLException {
    }

    @Override
    public void addUsers(DataSet user, String tableName) throws SQLException, IllegalAccessException {
    }

    @Override
    public <T extends DataSet> T getUser(Class<T> clazz, int id) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return null;
    }

    @Override
    public List<String> getAllNames() throws SQLException {
        return new ArrayList<>();
    }

    @Override
    public List<UsersDataSet> getAllUsers() throws SQLException {
        //todo: implement me please
        return new ArrayList<>();
    }

    @Override
    public void deleteTables() throws SQLException {
    }

    @Override
    public void close() throws Exception {
        connection.close();
        System.out.println("Connection closed");
        System.out.println();
    }

    protected Connection getConnection() {
        return connection;
    }


}
