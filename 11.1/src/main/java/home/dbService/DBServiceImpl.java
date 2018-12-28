package home.dbService;

import home.DBServiceException;
import home.base.DBService;
import home.base.DataSet;
import home.base.UsersDataSet;
import home.executor.Executor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBServiceImpl implements DBService {

    private final Connection connection;
    String tableName;
    private static String CREATE_TABLE = "create table if not exists %s (id bigint auto_increment, " +
            "name varchar(255), " + "age int(3), " + "primary key (id))";
    private static String INSERT_USER = "insert into %s (%s) values (%s)";
    private static String DELETE_TABLE = "drop table %s";
    private static String SELECT_USER = "select id, name, age from %s where id=%s";

    public DBServiceImpl() {
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
    public void prepareTables(Class clazz) throws DBServiceException {
        Executor exec = new Executor(getConnection());
        CREATE_TABLE = String.format(CREATE_TABLE, clazz.getSimpleName());
        exec.execUpdate(CREATE_TABLE);
        System.out.println("Table created");
        this.tableName=tableName;
    }

    @Override
    public <T extends DataSet> void save(T user) throws DBServiceException {
        try {
            Executor exec = new Executor(getConnection());
            Field[] fields = user.getClass().getDeclaredFields();

            StringBuilder values=new StringBuilder();
            for (int i=0; i<fields.length; i++){
                values.append('?') ;
                values.append(',') ;
            }
            values.deleteCharAt(values.length()-1);

            StringBuilder columns = new StringBuilder();
                for (Field field:fields) {
                    field.setAccessible(true);
                    columns.append(field.getName());
                    columns.append(',');
                }
            columns.deleteCharAt(columns.length()-1);

            INSERT_USER = String.format(INSERT_USER, user.getClass().getSimpleName(),columns,values);
            exec.execUpdate(INSERT_USER, statement -> {

                for (Field field:fields) {
                    field.setAccessible(true);
                    if (field.getName().equalsIgnoreCase("id")) {
                        try {
                            if (field.getLong(user)!=0){
                                statement.setLong(1, field.getLong(user));}
                            else {
                                statement.setNull(1, 0);
                            }
                        } catch (IllegalAccessException | SQLException e) {
                            throw new DBServiceException(e.toString());
                        }
                    }
                    if (field.getName().equalsIgnoreCase("name")) {
                        try {
                            statement.setString(2, (String) field.get(user));
                        } catch (SQLException | IllegalAccessException e) {
                            throw new DBServiceException(e.toString());
                        }
                    }
                    if (field.getName().equalsIgnoreCase("age")) {
                        try {
                            statement.setInt(3, field.getInt(user));
                        } catch (SQLException | IllegalAccessException e) {
                            throw new DBServiceException(e.toString());
                        }
                    }
                }
                try {
                    statement.executeUpdate();
                } catch (SQLException e) {
                    throw new DBServiceException(e.toString());
                }
                ResultSet ids;
                try {
                    ids = statement.getGeneratedKeys();
                } catch (SQLException e) {
                    throw new DBServiceException(e.toString());
                }
                try {
                    ids.next();
                } catch (SQLException e) {
                    throw new DBServiceException(e.toString());
                }
                try {
                    user.id= ids.getInt(1);
                } catch (SQLException e) {
                    throw new DBServiceException(e.toString());
                }
                try {
                    System.out.println("User added. Rows changed: " + statement.getUpdateCount());
                } catch (SQLException e) {
                    throw new DBServiceException(e.toString());
                }
            });
            getConnection().commit();
        } catch (SQLException e) {
            try {
                getConnection().rollback();
            } catch (SQLException e1) {
                throw new DBServiceException(e.toString());
            }
        } finally {
            try {
                getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                throw new DBServiceException(e.toString());
            }
        }
    }

    @Override
    public <T extends DataSet> T load(Class <T> clazz, int id) throws DBServiceException {
        Executor exec = new Executor(getConnection());

        DataSet dataSet = null;
        try {
            dataSet = exec.execQuery(String.format(SELECT_USER, clazz.getSimpleName(), id), result -> {
                try {
                    result.next();
                } catch (SQLException e) {
                    throw new DBServiceException(e.toString());
                }

                Class []params ={int.class, String.class, int.class};
                T user = null;
                try {
                    user = (T)clazz.getConstructor().newInstance();
                            //clazz.getConstructor(params).newInstance(result.getInt("id"),
                            //result.getString("name"), result.getInt("age"));

                    Field fields [] =clazz.getDeclaredFields();
                    for (Field field:fields){
                        field.setAccessible(true);
                        Field f = user.getClass().getDeclaredField(field.getName());
                        f.setAccessible(true);
                        f.set(user, result.getObject(field.getName()));
                        //user.field.getName()=result.getObject(field.getName());

                    }
                } catch (InstantiationException | NoSuchMethodException | IllegalAccessException |
                        InvocationTargetException | NoSuchFieldException | SQLException e) {
                    throw new DBServiceException(e.toString());

                }
                return  user;
                //new UsersDataSet (result.getInt("id"), result.getString("name"), result.getInt("age"));
            });
        } catch (DBServiceException e) {
            throw new DBServiceException(e.toString());
        }
        return (T) dataSet;
    }

    @Override
    public void deleteTables () throws DBServiceException {
        tableName="usersdataset";
        Executor exec = new Executor(getConnection());
        DELETE_TABLE = String.format(DELETE_TABLE, tableName);
        exec.execUpdate(DELETE_TABLE);
        System.out.println("Table dropped");
    }

    @Override
    public String getLocalStatus() {
        return null;
    }

    @Override
    public void save(UsersDataSet dataSet) {

    }

    @Override
    public UsersDataSet read(long id) {
        return null;
    }

    @Override
    public UsersDataSet readByName(String name) {
        return null;
    }

    @Override
    public List<UsersDataSet> readAll() {
        return null;
    }

    @Override
    public void shutdown() {

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
