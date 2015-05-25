package MyApp.services;

import MyApp.entities.User;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 21.05.15
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
public class UserAuthentImpl implements UserAuthent {
    @Inject

    private Session session;
    public boolean valid(String loginId, String password){
        List<User> usersLst = session.createCriteria(User.class)
                .add(Restrictions.eq("loginId", loginId))
                .add(Restrictions.eq("password", password  ))
                .list();
        if(usersLst.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

}
