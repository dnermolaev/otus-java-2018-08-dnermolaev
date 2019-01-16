package home.helpers;

import javax.servlet.http.HttpServletResponse;

public class SetCond {

    public static void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public static void setNotOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
