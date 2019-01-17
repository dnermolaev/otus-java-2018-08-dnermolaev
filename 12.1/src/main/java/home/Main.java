package home;

import home.base.DBService;
import home.dbService.DBServiceHibernateImpl;

/**
 * mysql> CREATE USER 'dima'@'localhost' IDENTIFIED BY 'dima';
 * mysql> GRANT ALL PRIVILEGES ON * . * TO 'dima'@'localhost';
 * mysql> select user, host from mysql.user;
 * mysql> create database db_examp;
 * mysql> SET GLOBAL time_zone = '+7:00';
 */

public class Main {

    public static void main(String[] args) throws Exception {

        DBService db = new DBServiceHibernateImpl();

        DBTestDataInitializer dbTestDataInitializer = new DBTestDataInitializer();
        dbTestDataInitializer.intDBWithTestData(db);

        ServerWrapper server = new ServerWrapper();
        server.run(db);
    }
}
