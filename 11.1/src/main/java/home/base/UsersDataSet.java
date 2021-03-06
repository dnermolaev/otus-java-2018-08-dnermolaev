package home.base;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UsersDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    private AdressDataSet homeAdress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDataSet")
    private List<PhoneDataSet> phones;

    public void addPhone(PhoneDataSet phone) {
        phones.add(phone);
        phone.setUserDataSet(this);
    }

    public UsersDataSet() {
    }

    public UsersDataSet(String name,int age, AdressDataSet homeAdress, List<PhoneDataSet> phones) {
        //this.setId(-1);
        id=0;
        this.name = name;
        this.age=age;
        this.homeAdress=homeAdress;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() { return age;    }

    public void setAge(int age) { this.age = age;  }

    public AdressDataSet getAdress() { return homeAdress; }

    public void setAdress(AdressDataSet homeAdress) {  this.homeAdress=homeAdress; }

    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    private void setPhones(List<PhoneDataSet> phones) {
        this.phones = phones;
    }

    @Override
    @Transient
    public String toString() {
        return "UserDataSet{" +
                "id'" + getId() + '\'' +
                " name='" + name + '\'' +
                ", age=" +age + '\''+
                ", homeAdress=" +homeAdress + '\''+
                ", phone=" + phones +
                '}';
    }
}
