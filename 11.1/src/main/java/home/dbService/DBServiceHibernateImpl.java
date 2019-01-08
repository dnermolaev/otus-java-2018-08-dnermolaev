package home.dbService;

import home.DBServiceException;
import home.base.*;
import home.dao.UsersDataSetDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class DBServiceHibernateImpl implements DBService {

    private final SessionFactory sessionFactory;

    public DBServiceHibernateImpl() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UsersDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);
        configuration.addAnnotatedClass(AdressDataSet.class);
        configuration.addAnnotatedClass(EmptyDataSet.class);

        /*configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_examp");
        configuration.setProperty("hibernate.connection.username", "dima");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");*/

                configuration.configure(new File("src/main/resources/hibernate.cfg.xml"));
                /*.addFile(new File("config/UsersDataSet.hbm.xml"))
                .addFile(new File("config/PhoneDataSet.hbm.xml"))
                .addFile(new File("config/AdressDataSet.hbm.xml"));*/

        sessionFactory = createSessionFactory(configuration);
    }

    public DBServiceHibernateImpl(Configuration configuration) {
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public String getMetaData()throws DBServiceException {

        try {
            Connection connection = sessionFactory. getSessionFactoryOptions().getServiceRegistry().
                    getService(ConnectionProvider.class).getConnection();

            DatabaseMetaData metaData = connection.getMetaData();
            sessionFactory.getSessionFactoryOptions().getServiceRegistry().
                    getService(ConnectionProvider.class).closeConnection(connection);
            return "Connected to: " + metaData.getURL() + "\n" +
                    "DB name: " + metaData.getDatabaseProductName() + "\n" +
                    "DB version: " +metaData.getDatabaseProductVersion() + "\n" +
                    "Driver: " + metaData.getDriverName();


        } catch (SQLException e) {
            new DBServiceException(e.toString());
            return e.getMessage();
        }
        finally {

        }

    }

    @Override
    public String getLocalStatus() {
        return runInSession(session -> {
            return session.getTransaction().getStatus().name();
        });
    }

    @Override
    public  void save(UsersDataSet user)throws DBServiceException {
        try (Session session = sessionFactory.openSession()) {
            UsersDataSetDAO dao = new UsersDataSetDAO(session);
            dao.save(user);
            //session.save(user);
        }
    }

    @Override
    public UsersDataSet load(long id) {
        return runInSession(session -> {
            UsersDataSetDAO dao = new UsersDataSetDAO(session);
            return dao.read(id);
        });
    }

    @Override
    public UsersDataSet readByName(String name) {
        return runInSession(session -> {
            UsersDataSetDAO dao = new UsersDataSetDAO(session);
            return dao.readByName(name);
        });
    }

    @Override
    public List<UsersDataSet> readAll() {
        return runInSession(session -> {
            UsersDataSetDAO dao = new UsersDataSetDAO(session);
            return dao.readAll();
        });
    }

    @Override
    public void close() {
        sessionFactory.close();
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }
}
