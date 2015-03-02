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
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual
    public long id;

    @Validate("required")
    public String name;

    @Validate("required")
    public String street;

    @Validate("required")
    public String House;

    @Validate("required")
    public String apartment ;

    @Validate("required")
    public String phone;
}