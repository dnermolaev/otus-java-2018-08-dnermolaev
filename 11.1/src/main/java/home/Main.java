package home;

import home.base.AdressDataSet;
import home.base.DBService;
import home.base.PhoneDataSet;
import home.base.UsersDataSet;
import home.dbService.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * mysql> CREATE USER 'dima'@'localhost' IDENTIFIED BY 'dima';
 * mysql> GRANT ALL PRIVILEGES ON * . * TO 'dima'@'localhost';
 * mysql> select user, host from mysql.user;
 * mysql> create database db_examp;
 * mysql> SET GLOBAL time_zone = '+7:00';
 */

public class Main {
    public static void main(String[] args) throws Exception {
        //DBService db =new DBServiceImpl();
        //db.prepareTables(UsersDataSet.class);
        //System.out.println(db.getMetaData());

        DBService db =new DBServiceHibernateImpl();
        System.out.println("Status: " + db.getLocalStatus());

        UsersDataSet user1 = new UsersDataSet("Dima",30, new AdressDataSet("Sovetskaya"),
                new ArrayList<PhoneDataSet>());
        user1.addPhone(new PhoneDataSet("555444"));
        user1.addPhone(new PhoneDataSet("123456"));
        UsersDataSet user2 = new UsersDataSet("Lida",28, new AdressDataSet("Lenina"),
                new ArrayList<PhoneDataSet>());
        user2.addPhone(new PhoneDataSet("987654"));
        UsersDataSet user3 = new UsersDataSet( "Donald",4, new AdressDataSet("Broadway"),
                new ArrayList<PhoneDataSet>());
        user3.addPhone(new PhoneDataSet("321654"));

        db.save(user1);
        db.save(user2);
        db.save(user3);
        System.out.println("--------------------------------------------------");
        UsersDataSet dataSet = db.read(1);
        System.out.println(dataSet);
        System.out.println("--------------------------------------------------");

        dataSet = db.readByName("" +
                "" +
                "Lida");
        System.out.println(dataSet);
        System.out.println("--------------------------------------------------");

        List<UsersDataSet> dataSets = db.readAll();
        for (UsersDataSet userDataSet : dataSets) {
            System.out.println(userDataSet);
        }
        System.out.println("--------------------------------------------------");

        db.shutdown();

        /*UsersDataSet user4 = db.load(UsersDataSet.class, 1);
        System.out.println(user4.toString());

        db.deleteTables();*/
    }


}
