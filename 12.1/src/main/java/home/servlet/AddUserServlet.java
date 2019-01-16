package home.servlet;

import home.DBServiceException;
import home.base.AdressDataSet;
import home.base.DBService;
import home.base.PhoneDataSet;
import home.base.UsersDataSet;
import home.helpers.AddUser;
import home.helpers.FieldsCheck;
import home.helpers.SetCond;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        boolean constructExc = FieldsCheck.check(request, PARAMETER_NAME);

        if (constructExc == false) {
            AddUser.add(db, request);
            String page = templateProcessor.getPage(ADDUSER_PAGE_TEMPLATE, new HashMap<>());
            response.getWriter().println(page);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            SetCond.setNotOK(response);
        }
    }
}
