package MyApp.pages;


import MyApp.entities.Comment;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 19.02.15
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class Book {
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
        return index;
    }




    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){

        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }
    };



    /*private void StartComment(){
        Comment comment = new Comment();
        comment.name = "1";
        comment.time ="1" ;
        comment.recall ="1" ;
        Transaction transaction = session.beginTransaction();
        session.persist(comment);
        transaction.commit();

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("dd MMMM HH:mm:ss", myDateFormatSymbols);
        comment1.time=  dateFormat.format( currentDate );
        session.persist(comment1);

        Comment comment2 =(Comment) session.load(Comment.class, 1);
        Transaction transaction2 = session.beginTransaction();
        session.delete(comment2);
        transaction.commit();


    }
    */


    public List<Comment> getComments() {

        session.createCriteria(Comment.class).list();
        return new ArrayList <Comment>();
    }

}
