package home.servlet;

import home.DBServiceException;
import home.base.DBService;
import home.base.UsersDataSet;

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
    DBService db;

    public GetUserNameServlet(TemplateProcessor templateProcessor, DBService db) {
        this.templateProcessor = templateProcessor;
        this.db=db;
    }

    private String getPage(String id) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(ID_VARIABLE_NAME, id == null ? "" : id);
        return templateProcessor.getPage(GETUSERNAME_PAGE_TEMPLATE, pageVariables);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String requestId = request.getParameter(ID_PARAMETER_NAME);


            if (requestId != null) {
                try {
                    setOK(response);
                    Long id = Long.parseLong(requestId);

                    String l = db.readNameById(id);
                    String page = getPage(l); //save to the page
                    response.getWriter().println(page);
                }
                catch (NullPointerException e){
                    setNotOK(response);
                    throw new NullPointerException ("There is no such id " + id.toString() +" in DB");
                }

            } else {
                setOK(response);
                String l = id;
                String page = getPage(l); //save to the page
                response.getWriter().println(page);
            }
        }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void saveToVariable(String requestId) {
        id = requestId != null ? requestId : id;
    }

    private void setNotOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
