package home.helpers;

import home.DBServiceException;
import home.base.AdressDataSet;
import home.base.DBService;
import home.base.PhoneDataSet;
import home.base.UsersDataSet;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RequestTransformer {

    public static void toUsersDataSet(DBService db, HttpServletRequest request){
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
    }
}
