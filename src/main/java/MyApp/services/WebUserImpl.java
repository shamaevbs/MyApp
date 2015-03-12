package MyApp.services;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 12.03.15
 * Time: 18:06
 * To change this template use File | Settings | File Templates.
 */
public class WebUserImpl implements WebUser {

    @Inject
    Request request;

    public long getUser() {
        Session session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
            long userId = new Date().getTime();
            session.setAttribute("WebUser", userId);
            return userId;
        } else {
            if (session.getAttribute("WebUser") != null) {
                return Long.valueOf(session.getAttribute("WebUser").toString());
            } else {
                long userId = new Date().getTime();
                session.setAttribute("WebUser", userId);
                return userId;
            }
        }
    }
}
