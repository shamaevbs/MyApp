package MyApp.pages;


import MyApp.entities.User;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 19.02.15
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class Registration {
    @Property
    private User user;
    @Inject
    private Session session;

    @Component(id = "registrationform")
    private Form form;
    @CommitAfter
    Object onSuccess()
    {

        try{
            if(user.password.compareTo(user.password2)!=0){
                throw new BadpasswordException();
            }
            List<User> userLst = session.createCriteria(User.class)
                    .add(Restrictions.eq("loginId", user.loginId))
                    .list();
            if(!userLst.isEmpty()){
                throw new UseLoginException();
            }

            session.persist(user);
            return LogIn.class;
        }
        catch (BadpasswordException e){
            String error = "passwords do not match";
            form.recordError(error);

        }
        catch (UseLoginException e){
            String error = "This login is not available ";
            form.recordError(error);
        }
        return Registration.class;
    }



    private class BadpasswordException extends Throwable {
    }

    private class UseLoginException extends Throwable {
    }
}
