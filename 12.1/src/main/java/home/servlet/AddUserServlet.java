package home.servlet;

import home.DBServiceException;
import home.base.AdressDataSet;
import home.base.DBService;
import home.base.PhoneDataSet;
import home.base.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddUserServlet extends HttpServlet {

    public static final String PARAMETER_NAME [] = {"name", "age", "street", "phonenumber"};
    private static final String ADDUSER_PAGE_TEMPLATE = "adduser.html";
    private final TemplateProcessor templateProcessor;
    DBService db;

    public AddUserServlet(TemplateProcessor templateProcessor, DBService db) {
        this.templateProcessor = templateProcessor;
        this.db=db;
    }

    private static Map<String, Object> createPageVariablesMap() {
        Map<String, Object> pageVariables = new HashMap<>();
        return pageVariables;}

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap();
        String page = templateProcessor.getPage(ADDUSER_PAGE_TEMPLATE, pageVariables);
        response.getWriter().println(page);
        response.setStatus(HttpServletResponse.SC_OK);
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Boolean constructExc=true;
        for (String name : PARAMETER_NAME) {
            String requestValue = request.getParameter(name);
            if (requestValue == null || requestValue.isEmpty()){
                constructExc = true;
                break;
            }
            else {constructExc = false;
            }
        }

        if (constructExc==false) {
            AdressDataSet adress = new AdressDataSet(request.getParameter("street"));
            PhoneDataSet phone = new PhoneDataSet(request.getParameter("phonenumber"));
            UsersDataSet user = new UsersDataSet(request.getParameter("name"),
                    Integer.valueOf(request.getParameter("age")), adress,
                    List.of(phone));
            try {
                db.save(user);
            } catch (DBServiceException e) {
                e.printStackTrace();
            }
            Map<String, Object> pageVariables = createPageVariablesMap();
            String page = templateProcessor.getPage(ADDUSER_PAGE_TEMPLATE, pageVariables);
            response.getWriter().println(page);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            setNotOK(response);
        }
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void setNotOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
