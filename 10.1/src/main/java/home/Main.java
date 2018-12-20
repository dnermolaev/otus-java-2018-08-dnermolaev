package home;

import home.base.DBService;
import home.base.UsersDataSet;
import home.connection.DBServiceConnection;
import home.connection.DBServiceGet;
import home.connection.DBServiceUpdate;



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
        DBService db =new DBServiceGet();

        UsersDataSet user1 = new UsersDataSet("Dima",30);
        UsersDataSet user2 = new UsersDataSet("Lida",28);
        UsersDataSet user3 = new UsersDataSet( 5, "Donald",4);


        user1.save();
        user2.save();
        user3.save();

        UsersDataSet user4 = UsersDataSet.load(1, UsersDataSet.class);
        System.out.println(user4.toString());


        db.deleteTables();
    }


}
