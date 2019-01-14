package home.base;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class EmptyDataSet {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
