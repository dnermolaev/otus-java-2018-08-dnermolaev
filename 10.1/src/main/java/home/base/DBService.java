package home.base;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface DBService extends AutoCloseable {
    String getMetaData();

    void prepareTables(String tableName) throws SQLException;

    void addUsers(DataSet user, String tableName) throws SQLException, IllegalAccessException;

    <T extends DataSet> T getUser(Class<T> clazz, int id) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    List<String> getAllNames() throws SQLException;

    List<UsersDataSet> getAllUsers() throws SQLException;

    void deleteTables() throws SQLException;
}
