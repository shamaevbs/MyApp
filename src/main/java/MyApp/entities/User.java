package MyApp.entities;

import org.apache.tapestry5.beaneditor.NonVisual;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 18.05.15
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual
    public Long id;

    public String login;
    public String password;
}
