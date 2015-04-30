package MyApp.pages;


import MyApp.entities.Comment;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 19.02.15
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class Book {
    @Property private Comment comment;
    @Inject private Session session;
    @Component(id = "commentform")
    private Form form;
    @Persist(PersistenceConstants.FLASH)
    @InjectPage
    private Pizza mainPage;

    @CommitAfter
    Object onSuccess()    {
        session.persist(comment);
        return mainPage;
    }

}
