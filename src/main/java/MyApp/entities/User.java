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
    public String loginId;
    public String password;
    public String mail;
    public String name;
    public String house;
    public String access;
    public String apartment;
    public String phone;

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getLoginId(){
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  String password2;



}
