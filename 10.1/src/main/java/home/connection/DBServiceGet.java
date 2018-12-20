package home.connection;

import home.base.DataSet;
import home.executor.Executor;
import java.lang.reflect.InvocationTargetException;

import java.sql.SQLException;

public class DBServiceGet extends DBServiceUpdate {
    private static String SELECT_USER = "select id, name, age from %s where id=%s";

    @Override
    public <T extends DataSet> T getUser(Class <T> clazz, int id) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Executor exec = new Executor(getConnection());

        DataSet dataSet =exec.execQuery(String.format(SELECT_USER, clazz.getSimpleName(), id), result -> {
                result.next();

            Class []params ={int.class, String.class, int.class};
            T user =clazz.getConstructor(params).newInstance(result.getInt("id"),
                    result.getString("name"), result.getInt("age"));
            return  user;
                    //new UsersDataSet (result.getInt("id"), result.getString("name"), result.getInt("age"));
        });
    return (T) dataSet;
    }



        /*//with lambda
        exec.execQuery(String.format(SELECT_USER, id),
                result -> {
                    result.next();
                    System.out.println("Read user: " + result.getString("name"));
                });*/




   /* private static class ResultHandlerGetName implements ResultHandler {
        public void handle(ResultSet result) throws SQLException {
            result.next();
            System.out.println("Read user: " + result.getString("name")+result.getInt("age"));
        }
    }*/
}
