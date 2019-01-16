package home.servlet;

import home.base.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TotalQuantityServlet extends HttpServlet {

    private static final String TOTAL_PAGE_TEMPLATE = "totalquantity.html";
    public static DBService db;

    private final TemplateProcessor templateProcessor;

    public TotalQuantityServlet(TemplateProcessor templateProcessor, DBService db) {
        this.templateProcessor = templateProcessor;
        this.db = db;
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("quantity", db.getQuantity());

        return pageVariables;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = createPageVariablesMap(request);

        response.setContentType("text/html;charset=utf-8");
        String page = templateProcessor.getPage(TOTAL_PAGE_TEMPLATE, pageVariables);
        response.getWriter().println(page);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
