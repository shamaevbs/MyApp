package MyApp.services;

import MyApp.entities.User;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 21.05.15
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
public interface UserAuthent {

   public boolean isValid(String loginId, String password);
   public User authenticateUser (String loginId, String password);
}
