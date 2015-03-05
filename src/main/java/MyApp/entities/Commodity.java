package MyApp.entities;

import org.apache.tapestry5.beaneditor.NonVisual;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 12.02.15
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual
    public long id;
    public void setId(long id) {
        this.id = id;
    }

    //@Validate("required")
    public String price;
    public void setPrice(String price) {
        this.price = price;
    }

    //@Validate("required")
    public String name;
    public void setName(String name) {
        this.name = name;
    }

    public String number;
    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() { return number; }

    public long getId() { return id; }




}
