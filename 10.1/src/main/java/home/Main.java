package home;

import home.base.DBService;
import home.base.UsersDataSet;
import home.connection.DBServiceImpl;

import java.lang.reflect.Constructor;

/**
 * mysql> CREATE USER 'dima'@'localhost' IDENTIFIED BY 'dima';
 * mysql> GRANT ALL PRIVILEGES ON * . * TO 'dima'@'localhost';
 * mysql> select user, host from mysql.user;
 * mysql> create database db_examp;
 * mysql> SET GLOBAL time_zone = '+7:00';
 */

public class Main {
    public static void main(String[] args) throws Exception {
        DBService db =new DBServiceImpl();
        System.out.println(db.getMetaData());
        db.prepareTables(UsersDataSet.class);


        UsersDataSet user1 = new UsersDataSet("Dima",30);
        UsersDataSet user2 = new UsersDataSet("Lida",28);
        UsersDataSet user3 = new UsersDataSet( 5, "Donald",4);

        db.save(user1);
        db.save(user2);
        db.save(user3);

        UsersDataSet user4 = db.load(UsersDataSet.class, 1);
        System.out.println(user4.toString());

        db.deleteTables();
    }


}
