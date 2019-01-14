package home;

import home.base.AdressDataSet;
import home.base.DBService;
import home.base.PhoneDataSet;
import home.base.UsersDataSet;
import home.dbService.*;
import home.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.sql.JDBCType;
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
    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws Exception {
        //JdbcDBHelper jdbc=new JdbcDBHelper();
        //jdbc.prepareTables(UsersDataSet.class);
        //DBService db =new DBServiceImpl();
        //System.out.println(db.getMetaData());

        DBService db =new DBServiceHibernateImpl();
        System.out.println("Status: " + db.getLocalStatus());
        System.out.println(db.getMetaData());

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        TemplateProcessor templateProcessor = new TemplateProcessor();

        context.addServlet(new ServletHolder(new LoginServlet(templateProcessor, "anonymous")), "/login");
        context.addServlet(AdminServlet.class, "/admin");
        context.addServlet(new ServletHolder(new GetUserNameServlet(templateProcessor, db)),"/getusername");
        context.addServlet(new ServletHolder(new AddUserServlet(templateProcessor, db)),"/adduser");
        context.addServlet(new ServletHolder(new TotalQuantityServlet(templateProcessor, db)), "/totalquantity");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

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

        server.start();
        server.join();
        /*UsersDataSet dataSet = db.load(1);
        System.out.println(dataSet.toString());
        System.out.println("--------------------------------------------------");*/

        /*dataSet = db.readByName("" +
                "" +
                "Lida");
        System.out.println(dataSet);
        System.out.println("--------------------------------------------------");

        List<UsersDataSet> dataSets = db.readAll();
        for (UsersDataSet userDataSet : dataSets) {
            System.out.println(userDataSet);
        }
        System.out.println("--------------------------------------------------");*/

       //db.close();
        // jdbc.deleteTables();
    }


}
