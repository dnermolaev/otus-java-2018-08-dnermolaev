package home.base;

import javax.persistence.*;

@MappedSuperclass
public class DataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }
}