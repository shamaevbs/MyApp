package MyApp.entities;

import org.apache.tapestry5.beaneditor.NonVisual;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 30.04.15
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonVisual
    public long id;
    public String name;
    public String call;
    public  String time;

}
