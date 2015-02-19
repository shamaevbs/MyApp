package MyApp.entities;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual
    public Long id;

    @Validate("required")
    public String price;

    @Validate("required")
    public String name;

    @Validate("required")
    public String specification;



   // @Validate("required")
    //public String firstName;
    //@Validate("required")
    //public String lastName;

    //public String street1;

    //public String street2;
    //@Validate("required")
    //public String city;
    //@Validate("required")
    //public String state;
    //@Validate("required,regexp")
    //public String zip;
    //@Validate("required")
    //public String email;
    //public String phone;
}
