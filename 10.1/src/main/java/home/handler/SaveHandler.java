package home.handler;

import home.base.DBService;
import home.base.DataSet;
import home.connection.DBServiceUpdate;


public class SaveHandler {

    public static <T extends DataSet> void save(T user) throws Exception {

        try (DBService dbService = new DBServiceUpdate()) {
            System.out.println(dbService.getMetaData());
            dbService.prepareTables(user.getClass().getSimpleName());
            dbService.addUsers(user, user.getClass().getSimpleName());
        }
    }




        }



