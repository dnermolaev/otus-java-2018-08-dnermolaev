package home.base;

import home.connection.DBServiceImpl;
import home.handler.SaveHandler;

public class UsersDataSet extends DataSet {
    private final long id;
    private final String name;
    private int age;

    public UsersDataSet() {
        id=0;
        name=null;
        age=0;
    }

    public UsersDataSet(long id, String name, int age) {
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
}
