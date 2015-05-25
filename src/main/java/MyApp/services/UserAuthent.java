package MyApp.services;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 21.05.15
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
public interface UserAuthent {

   public boolean valid(String loginId, String password);
}
