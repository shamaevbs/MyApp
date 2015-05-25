package MyApp.pages;

import MyApp.entities.User;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 21.05.15
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */



public class Visit  {

    private Long myUserId = null;
    private String myLoginId = null;

    public Visit(User user) {
        myUserId = user.getId();
        cacheUsefulStuff(user);
    }

    public void noteChanges(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        else if (user.getId().equals(myUserId)) {
            cacheUsefulStuff(user);
        }
    }

    private void cacheUsefulStuff(User user) {
        myLoginId = user.getLoginId();
    }

    public Long getMyUserId() {
        return myUserId;
    }

    public String getMyLoginId() {
        return myLoginId;
    }

}
