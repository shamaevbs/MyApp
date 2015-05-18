package MyApp.entities;

import org.apache.tapestry5.beaneditor.NonVisual;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;


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
    public BigDecimal price;
    public String name;
    //public String specification;
    public String number;
    public long product;
    public long client;
    public long amt;
    public long app;
    public BigDecimal getPrice() {
        return price.setScale(2,BigDecimal.ROUND_DOWN);
    }

    //public long amt;


    //public String basket;





}
