package home.helpers;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

    public static boolean isAllAttributesExists(HttpServletRequest request, String[] names) {
        boolean constructExc = true;
        for (String name : names) {
            String requestValue = request.getParameter(name);
            if (requestValue == null || requestValue.isEmpty()) {
                constructExc = true;
                break;
            } else {
                constructExc = false;
            }
        }
        return constructExc;
    }
}
