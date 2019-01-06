package home.base;

import home.DBServiceException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface DBService extends AutoCloseable {
    String getMetaData() throws DBServiceException;

    void save(UsersDataSet user)throws DBServiceException;

    //<T extends DataSet> T load(Class<T> clazz, int id) throws DBServiceException;

    UsersDataSet load(long id) throws DBServiceException;

    void close();


    String getLocalStatus();

    UsersDataSet readByName(String name);

    List<UsersDataSet> readAll();

}
