package MyApp.pages;

import MyApp.entities.Application;
import MyApp.entities.Commodity;
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
    @Property @Persist private Commodity[] commodity = new Commodity[10];
    @Persist(PersistenceConstants.FLASH)
    private String message;
    //private Index index;
    @CommitAfter
    Object onSuccess()
    {
        // session.persist(address);
        session.persist(application);
        message= String.valueOf(0);
        return null;

    }
    void setup( long productID) {
        this.productID = productID;
        //Product product = (Product) session.createCriteria(Product.class).add(Restrictions.eq("name", "a")).uniqueResult();
        Product product = (Product) session.get(Product.class, productID);
        if (product != null) {
            commodity[0].name = product.getName();
            commodity[0].price = product.getPrice();
            //message = String.format("Your guess of %d is too %s.", 0, 0 < 1 ? commodity[0].name : "high");

        }
    }
}
