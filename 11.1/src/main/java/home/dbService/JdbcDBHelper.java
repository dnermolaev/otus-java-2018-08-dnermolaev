package home.dbService;

import home.DBServiceException;
import home.executor.Executor;

import java.sql.Connection;

public class JdbcDBHelper {

    private final Connection connection;
    String tableName;
    private static String CREATE_TABLE = "create table if not exists %s (id bigint auto_increment, " +
            "name varchar(255), " + "age int(3), " + "homeAdress varchar (255)," +
            "phones varchar (255), " + "primary key (id))";
    private static String DELETE_TABLE = "drop table %s";

    public JdbcDBHelper() {
        connection = ConnectionHelper.getConnection();
    }

    public void prepareTables(Class clazz) throws DBServiceException {
        Executor exec = new Executor(getConnection());
        CREATE_TABLE = String.format(CREATE_TABLE, clazz.getSimpleName());
        exec.execUpdate(CREATE_TABLE);
        System.out.println("Table created");
    }

    public void deleteTables () throws DBServiceException {
        tableName="usersdataset";
        Executor exec = new Executor(getConnection());
        DELETE_TABLE = String.format(DELETE_TABLE, tableName);
        exec.execUpdate(DELETE_TABLE);
        System.out.println("Table dropped");
    }

    protected Connection getConnection() {
        return connection;
    }
}
