package MyApp.pages;


import MyApp.entities.Comment;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 19.02.15
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class Book {
    @Property private Comment comment;
    @Property private Comment comment1;
    @Inject private Session session;
    /*@Component(id = "commentform")
    private Form form;
    //@Persist(PersistenceConstants.FLASH)
    @InjectPage
    private Pizza mainPage;
    */

    @InjectPage
    private Index index;
    @CommitAfter
    Object onSuccess()
    {
        // session.persist(address);
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("dd MMMM HH:mm:ss", myDateFormatSymbols);
        comment1.time=  dateFormat.format( currentDate );
        session.persist(comment1);
        return index;
    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){

        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }
    };

    public List<Comment> getComments() {
        return session.createCriteria(Comment.class)
                .list();

    }
}
