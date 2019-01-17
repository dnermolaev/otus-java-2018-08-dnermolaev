package home.servlet;

import home.base.DBService;
import home.helpers.RequestTransformer;
import home.helpers.RequestHelper;
import home.helpers.ResponseHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class AddUserServlet extends HttpServlet {

    public static final String PARAMETER_NAME[] = {"name", "age", "street", "phonenumber"};
    private static final String ADDUSER_PAGE_TEMPLATE = "adduser.html";
    private final TemplateProcessor templateProcessor;
    private DBService db;

    public AddUserServlet(TemplateProcessor templateProcessor, DBService db) {
        this.templateProcessor = templateProcessor;
        this.db = db;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String page = templateProcessor.getPage(ADDUSER_PAGE_TEMPLATE, new HashMap<>());
        response.getWriter().println(page);
        response.setStatus(HttpServletResponse.SC_OK);
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        boolean constructExc = RequestHelper.isAllAttributesExists(request, PARAMETER_NAME);

        if (constructExc == false) {
            RequestTransformer.toUsersDataSet(db, request);
            String page = templateProcessor.getPage(ADDUSER_PAGE_TEMPLATE, new HashMap<>());
            response.getWriter().println(page);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            ResponseHelper.setBadRequestStatusv(response);
        }
    }
}
