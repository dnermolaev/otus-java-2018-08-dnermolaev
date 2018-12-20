package home.base;

import home.connection.DBServiceGet;
import home.handler.SaveHandler;

public class UsersDataSet extends DataSet {
    private final int id;
    private final String name;
    private int age;

    public UsersDataSet(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age=age;
    }

    public UsersDataSet( String name, int age) {
        id=0;
        this.name = name;
        this.age=age;
    }


    /*public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getAge() { return age;  }*/

    @Override
    public String toString() {
        return "UsersDataSet{" + "id=" + id + ", name='" + name + '\'' + ", age=" +age + '}';
    }

    public <T extends DataSet> void save() throws Exception {
        SaveHandler handler = new SaveHandler();
        handler.save(this);
    }

    public static <T extends DataSet> T load(long id, Class<T> clazz) throws Exception {
        try (DBService dbService = new DBServiceGet()) {
            System.out.println(dbService.getMetaData());
            return (T) dbService.getUser(clazz, (int) id);
        }
    }
}
