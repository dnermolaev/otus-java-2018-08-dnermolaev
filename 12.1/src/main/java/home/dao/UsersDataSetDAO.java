package home.dao;

import home.base.UsersDataSet;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UsersDataSetDAO {
    private Session session;

    public UsersDataSetDAO(Session session) {
        this.session = session;
    }

    public void save(UsersDataSet dataSet) {
        session.save(dataSet);
    }

    public UsersDataSet read(long id) {
        return session.load(UsersDataSet.class, id);
    }

    public UsersDataSet readByName(String name) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UsersDataSet> criteria = builder.createQuery(UsersDataSet.class);
        Root<UsersDataSet> from = criteria.from(UsersDataSet.class);
        criteria.where(builder.equal(from.get("name"), name));
        Query<UsersDataSet> query = session.createQuery(criteria);
        return query.uniqueResult();
    }

    public String readNameById(long id) {
        String name = String.valueOf(session.createQuery(
                "SELECT name FROM UsersDataSet WHERE id=:id")
                .setParameter("id", id)
                .uniqueResult());
            return name;
    }

    public List<UsersDataSet> readAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UsersDataSet> criteria = builder.createQuery(UsersDataSet.class);
        criteria.from(UsersDataSet.class);
        return session.createQuery(criteria).list();
//        List<UserDataSet> persons = session.createQuery(
//               "SELECT u FROM UserDataSet u WHERE name=:name")
//                .setParameter("name","Vasya")
//                .getResultList();
    }

    public Long getQuantity() {
        Long quantity = (Long) session.createQuery(
                "SELECT COUNT (id) FROM UsersDataSet")
                .getSingleResult();

        return quantity;
    }
}
