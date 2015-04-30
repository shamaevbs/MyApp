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
    @Validate("required")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Validate("required")
    public String call;

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }
    @Validate("required")
    public  String time;
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
