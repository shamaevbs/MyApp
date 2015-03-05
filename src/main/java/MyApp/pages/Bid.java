package MyApp.pages;

import MyApp.entities.Application;
import MyApp.entities.Product;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

public class Bid
{
    @Property
    private Application application;

    @Inject
    private Session session;

    //@InjectPage
    @Property @Persist private long productID;
    @Property @Persist private String commodity;
    @Property @Persist private String name;
    @Property @Persist(PersistenceConstants.FLASH)
    private String message;
    //private Index index;
    @CommitAfter
    Object onSuccess()
    {
        session.persist(application);
        message= String.format(" ");
        return null;

    }
    void setup( long productID) {
        this.productID = productID;
        //Product product = (Product) session.createCriteria(Product.class).add(Restrictions.eq("name", "a")).uniqueResult();
        Product product = (Product) session.get(Product.class, productID);
        if (product != null) {
            commodity = product.getName();
            //commodity[1].price = product.getPrice();
            if (message==null){
                message ="";
            }
            message = String.format(message+" "+product.getPrice()+ " "+  product.getName()+"," );
            //message= String.valueOf(0);
        }
    }
}
