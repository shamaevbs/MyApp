package MyApp.pages;


import MyApp.entities.Comment;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
    @Property @Persist(PersistenceConstants.FLASH)
    private String testB;

    @Property
    private Comment comment;

    @Inject
    private Session session;

    @InjectPage
    private Index index;
    @CommitAfter
    Object onSuccess()
    {
        session.persist(comment);
        List<Comment> commentLst = session.createCriteria(Comment.class)
                .add(Restrictions.eq("time", ""))
                .list();

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("dd MMMM HH:mm:ss", myDateFormatSymbols);
        testB=String.valueOf(dateFormat.format( currentDate ));
        for(Comment comment1 : commentLst) {
            comment1.time=String.valueOf(dateFormat.format( currentDate ))  ;
           session.persist(comment1);
        }

        /*session.save(comment1);
        transaction.commit(); */
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
        return session.createCriteria(Comment.class).list();
    }

}
