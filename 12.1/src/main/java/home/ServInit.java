package home;

import home.base.DBService;
import home.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;

public class ServInit {
    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";

    public ServInit() {
    }

    public void run (DBService db) throws Exception {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        TemplateProcessor templateProcessor = new TemplateProcessor();

        context.addServlet(new ServletHolder(new LoginServlet(templateProcessor, "anonymous")), "/login");
        context.addServlet(AdminServlet.class, "/admin");
        context.addServlet(new ServletHolder(new GetUserNameServlet(templateProcessor, db)), "/getusername");
        context.addServlet(new ServletHolder(new AddUserServlet(templateProcessor, db)), "/adduser");
        context.addServlet(new ServletHolder(new TotalQuantityServlet(templateProcessor, db)), "/totalquantity");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }
}
