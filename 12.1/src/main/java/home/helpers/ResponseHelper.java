package home.helpers;

import javax.servlet.http.HttpServletResponse;

public class ResponseHelper {

    public static void setOKStatus(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public static void setBadRequestStatusv(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
