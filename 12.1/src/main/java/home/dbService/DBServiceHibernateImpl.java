package home.dbService;

import home.DBServiceException;
import home.base.*;
import home.dao.UsersDataSetDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class DBServiceHibernateImpl implements DBService {

    private final SessionFactory sessionFactory;
    Transaction trans = null;


    public DBServiceHibernateImpl() {
        Configuration configuration = new Configuration();

        this.sessionFactory = createSessionFactory(new Configuration());

    }

    public DBServiceHibernateImpl(Configuration configuration) {
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {

        return configuration.configure().buildSessionFactory();

    }

    @Override
    public String getMetaData() throws DBServiceException {

        try {
            Connection connection = sessionFactory.getSessionFactoryOptions().getServiceRegistry().
                    getService(ConnectionProvider.class).getConnection();

            DatabaseMetaData metaData = connection.getMetaData();
            sessionFactory.getSessionFactoryOptions().getServiceRegistry().
                    getService(ConnectionProvider.class).closeConnection(connection);
            return "Connected to: " + metaData.getURL() + "\n" +
                    "DB name: " + metaData.getDatabaseProductName() + "\n" +
                    "DB version: " + metaData.getDatabaseProductVersion() + "\n" +
                    "Driver: " + metaData.getDriverName();


        } catch (SQLException e) {
            new DBServiceException(e.toString());
            return e.getMessage();
        } finally {

        }

    }

    @Override
    public String getLocalStatus() {
        return runInSession(session -> {
            return session.getTransaction().getStatus().name();
        });
    }

    @Override
    public void save(UsersDataSet user) throws DBServiceException {
        try (Session session = sessionFactory.openSession()) {
            trans = session.beginTransaction();
            UsersDataSetDAO dao = new UsersDataSetDAO(session);
            dao.save(user);
            //session.save(user);

            trans.commit();
        } catch (HibernateException e) {
            if (trans != null) {
                trans.rollback();
            }
            e.printStackTrace();
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
    public String readNameById(long id) {
        return runInSession(session -> {
            UsersDataSetDAO dao = new UsersDataSetDAO(session);
            return dao.readNameById(id);
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
    public Long getQuantity() {
        return runInSession(session -> {
            UsersDataSetDAO dao = new UsersDataSetDAO(session);
            return dao.getQuantity();
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
