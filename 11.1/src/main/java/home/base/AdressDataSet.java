package home.base;

import javax.persistence.*;

@Entity
@Table(name = "adress")
public class AdressDataSet extends DataSet {

    @Column(name = "street")
    private String street;

    /*@OneToOne
    private UsersDataSet userDataSet;

   public void setUsersDataSet(UsersDataSet userDataSet) {this.userDataSet = userDataSet;}

    public UsersDataSet getUserDataSet() {
        return userDataSet;
    }*/

    public AdressDataSet() {
    }

    public AdressDataSet(String street) {
        this.street = street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    @Override
    @Transient
    public String toString() {
        return street;
        /*"AdressDataSet{" +
                "street='" + street + '\'' +
                '}';*/
    }
}
