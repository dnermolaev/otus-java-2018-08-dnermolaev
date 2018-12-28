package home.base;

import home.DBServiceException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface DBService extends AutoCloseable {
    String getMetaData();

    void prepareTables(Class clazz) throws DBServiceException;

    <T extends DataSet> void save(T user) throws DBServiceException;

    <T extends DataSet> T load(Class<T> clazz, int id) throws DBServiceException;

    void deleteTables() throws DBServiceException;



    String getLocalStatus();

    void save(UsersDataSet dataSet);

    UsersDataSet read(long id);

    UsersDataSet readByName(String name);

    List<UsersDataSet> readAll();

    void shutdown();
}
