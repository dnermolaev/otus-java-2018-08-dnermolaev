package home;

import home.base.AdressDataSet;
import home.base.DBService;
import home.base.PhoneDataSet;
import home.base.UsersDataSet;
import home.dbService.DBServiceHibernateImpl;

import java.util.ArrayList;

public class DBInit {

    public DBInit() throws DBServiceException {

    }


    public DBService putData () throws DBServiceException {
        DBService db=new DBServiceHibernateImpl();
        UsersDataSet user1 = new UsersDataSet("Dima", 30, new AdressDataSet("Sovetskaya"),
                new ArrayList<PhoneDataSet>());
        user1.addPhone(new PhoneDataSet("555444"));
        user1.addPhone(new PhoneDataSet("123456"));
        UsersDataSet user2 = new UsersDataSet("Lida", 28, new AdressDataSet("Lenina"),
                new ArrayList<PhoneDataSet>());
        user2.addPhone(new PhoneDataSet("987654"));
        UsersDataSet user3 = new UsersDataSet("Donald", 4, new AdressDataSet("Broadway"),
                new ArrayList<PhoneDataSet>());
        user3.addPhone(new PhoneDataSet("321654"));

        db.save(user1);
        db.save(user2);
        db.save(user3);
        return db;
    }
}
