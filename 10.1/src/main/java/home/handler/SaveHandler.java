package home.handler;

import home.DBServiceException;
import home.base.DBService;
import home.base.DataSet;
import home.connection.DBServiceImpl;


public class SaveHandler {

    public static <T extends DataSet> void save(T user) throws DBServiceException {

        try (DBService dbService = new DBServiceImpl()) {
            System.out.println(dbService.getMetaData());
            dbService.prepareTables(user.getClass());
            dbService.save(user);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }




        }



