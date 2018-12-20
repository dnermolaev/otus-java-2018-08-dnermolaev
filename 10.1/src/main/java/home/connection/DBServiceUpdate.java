package home.connection;

import home.base.DataSet;
import home.executor.Executor;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBServiceUpdate extends DBServiceConnection {
    String tableName;
    public ResultSet ids;
    private static String CREATE_TABLE = "create table if not exists %s (id bigint auto_increment, " +
            "name varchar(255), " + "age int(3), " + "primary key (id))";
    private static String INSERT_USER = "insert into %s (id, name, age) values (?,?,?)";
    private static String DELETE_TABLE = "drop table %s";

    @Override
    public void prepareTables(String tableName) throws SQLException {
        Executor exec = new Executor(getConnection());
        CREATE_TABLE = String.format(CREATE_TABLE, tableName);
        exec.execUpdate(CREATE_TABLE);
        System.out.println("Table created");
        this.tableName=tableName;
    }

    @Override
    public void addUsers(DataSet user, String tableName) throws SQLException, IllegalAccessException {
        try {
            Executor exec = new Executor(getConnection());
            getConnection().setAutoCommit(false);
            INSERT_USER = String.format(INSERT_USER, tableName);
            exec.execUpdate(INSERT_USER, statement -> {
                    Field[] fields = user.getClass().getDeclaredFields();
                    for (Field field:fields) {
                        field.setAccessible(true);
                        if (field.getName().equalsIgnoreCase("id")) {
                            if (field.getInt(user)!=0){
                            statement.setInt(1, field.getInt(user));}
                            else {
                                statement.setNull(1, 0);
                            }
                        }
                        if (field.getName().equalsIgnoreCase("name")) {
                            statement.setString(2, (String) field.get(user));
                        }
                        if (field.getName().equalsIgnoreCase("age")) {
                            statement.setInt(3, field.getInt(user));
                        }
                    }
                    statement.executeUpdate();
                    ids = statement.getGeneratedKeys();
                    ids.next();
                user.id= Long.valueOf(ids.getInt(1));

                    System.out.println("User added. Rows changed: " + statement.getUpdateCount());
            });
            getConnection().commit();
        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            getConnection().setAutoCommit(true);
        }
    }
        @Override
        public void deleteTables () throws SQLException {
            tableName="usersdataset";
            Executor exec = new Executor(getConnection());
            DELETE_TABLE = String.format(DELETE_TABLE, tableName);
            exec.execUpdate(DELETE_TABLE);
            System.out.println("Table dropped");
        }
    }

