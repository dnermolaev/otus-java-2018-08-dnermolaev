package home.servlet;

import home.DBServiceException;
import home.base.DBService;
import home.base.UsersDataSet;
import home.helpers.FieldsCheck;
import home.helpers.SetCond;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetUserNameServlet extends HttpServlet {

    public static final String ID_PARAMETER_NAME = "id";
    private static final String ID_VARIABLE_NAME = "userName";
    private static final String GETUSERNAME_PAGE_TEMPLATE = "getusername.html";

    private final TemplateProcessor templateProcessor;
    private String id;
    public DBService db;

    public GetUserNameServlet(TemplateProcessor templateProcessor, DBService db) {
        this.templateProcessor = templateProcessor;
        this.db = db;
    }

    private String getPage(String id) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(ID_VARIABLE_NAME, id == null ? "" : id);
        return templateProcessor.getPage(GETUSERNAME_PAGE_TEMPLATE, pageVariables);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String page = getPage(" ");
        response.getWriter().println(page);
        response.setStatus(HttpServletResponse.SC_OK);
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String requestId = request.getParameter(ID_PARAMETER_NAME);

        if (requestId != null) {
            Long id = Long.parseLong(requestId);

            String l = db.readNameById(id);

            if (l.equals("null")) {
                SetCond.setNotOK(response);
            } else {
                SetCond.setOK(response);
                String page = getPage(l);
                response.getWriter().println(page);
            }
        } else {
            SetCond.setNotOK(response);
        }
    }
}
