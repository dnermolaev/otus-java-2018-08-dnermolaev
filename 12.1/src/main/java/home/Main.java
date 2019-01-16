package home;

import home.base.DBService;

/**
 * mysql> CREATE USER 'dima'@'localhost' IDENTIFIED BY 'dima';
 * mysql> GRANT ALL PRIVILEGES ON * . * TO 'dima'@'localhost';
 * mysql> select user, host from mysql.user;
 * mysql> create database db_examp;
 * mysql> SET GLOBAL time_zone = '+7:00';
 */

public class Main {
    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws Exception {

        DBInit dbInit = new DBInit();

        ServInit servInit = new ServInit();
        servInit.run(dbInit.putData());


    }


}
