package home.base;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class PhoneDataSet extends DataSet {

    @Column(name = "number")
    private String number;

    public UsersDataSet getUserDataSet() {
        return userDataSet;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ManyToOne
    private UsersDataSet userDataSet;

    public void setUserDataSet(UsersDataSet userDataSet) {
        this.userDataSet = userDataSet;
    }

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number;
                /*"PhoneDataSet{" +
                "number='" + number + '\'' +
                '}';*/
    }
}
