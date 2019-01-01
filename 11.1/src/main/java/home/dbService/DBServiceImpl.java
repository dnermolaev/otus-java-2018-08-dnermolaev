package home.dbService;

import home.DBServiceException;
import home.base.DBService;
import home.base.DataSet;
import home.base.UsersDataSet;
import home.executor.Executor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DBServiceImpl implements DBService {

    private final Connection connection;
    String tableName;
    private static String INSERT_USER = "insert into %s (%s) values (%s)";
    private static String SELECT_USER = "select * from %s where id=%s";

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
                   /* if (field.getName().equalsIgnoreCase("id")) {
                        try {
                            if (field.getLong(user)!=0){
                                statement.setLong(1, field.getLong(user));}
                            else {
                                statement.setNull(1, 0);
                            }
                        } catch (IllegalAccessException | SQLException e) {
                            throw new DBServiceException(e.toString());
                        }
                    }*/
                    if (field.getName().equalsIgnoreCase("name")) {
                        try {
                            statement.setString(1, (String) field.get(user));
                        } catch (SQLException | IllegalAccessException e) {
                            throw new DBServiceException(e.toString());
                        }
                    }
                    if (field.getName().equalsIgnoreCase("age")) {
                        try {
                            statement.setInt(2, field.getInt(user));
                        } catch (SQLException | IllegalAccessException e) {
                            throw new DBServiceException(e.toString());
                        }
                    }
                    if (field.getName().equalsIgnoreCase("homeAdress")) {
                        try {
                            statement.setString(3, String.valueOf(field.get(user)));
                        } catch (SQLException | IllegalAccessException e) {
                            throw new DBServiceException(e.toString());
                        }
                    }
                    if (field.getName().equalsIgnoreCase("phones")) {
                        try {
                            statement.setString(4, field.get(user).toString());
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
    public UsersDataSet load(long id) throws DBServiceException {
        Executor exec = new Executor(getConnection());

        UsersDataSet dataSet = null;
        try {
            dataSet = exec.execQuery(String.format(SELECT_USER, "UsersDataSet", id), result -> {
                try {
                    result.next();
                } catch (SQLException e) {
                    throw new DBServiceException(e.toString());
                }
                UsersDataSet user = null;
                try {
                    user = new UsersDataSet();
                    Class clazz=UsersDataSet.class;
                    Field fields [] =clazz.getDeclaredFields();
                    for (Field field:fields){
                        field.setAccessible(true);
                        Field f = user.getClass().getDeclaredField(field.getName());
                        f.setAccessible(true);
                        //System.out.println(f.getType());
                        //System.out.println(f.getGenericType() instanceof ParameterizedType);
                        if (f.getType().isPrimitive()==true)
                        {f.set(user, result.getObject(field.getName()));}
                        else {
                            Class []params ={result.getObject(field.getName()).getClass()};
                            if (f.getGenericType() instanceof ParameterizedType) {
                                ParameterizedType aType = (ParameterizedType) f.getGenericType();
                                Type[] fieldArgTypes = aType.getActualTypeArguments();
                                for (Type fieldArgType : fieldArgTypes) {
                                    Class fieldArgClass = (Class) fieldArgType;
                                    List <Object> list = new ArrayList<>();
                                    list.add(fieldArgClass.getConstructor(params).
                                        newInstance(result.getObject(field.getName())));
                                    f.set(user, list);
                                }
                            }
                            else {
                            f.set(user, f.getType().getConstructor(params).
                                    newInstance(result.getObject(field.getName())));}
                        }
                    }
                } catch (IllegalAccessException | NoSuchFieldException | SQLException |
                        InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                    throw new DBServiceException(e.toString());
                }
                return  user;
            });
        } catch (DBServiceException e) {
            throw new DBServiceException(e.toString());
        }
        return dataSet;
    }

    @Override
    public String getLocalStatus() {
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
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection closed");
        System.out.println();
    }

    protected Connection getConnection() {
        return connection;
    }


}
