package MyApp.state;





import MyApp.entities.User;

@SuppressWarnings("serial")
public class Visit {
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
