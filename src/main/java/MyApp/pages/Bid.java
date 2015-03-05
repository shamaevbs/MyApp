package MyApp.pages;

import MyApp.entities.Application;
import MyApp.entities.Product;
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
    @Property @Persist private String price;
    int j;
    long[] a = new long[10];

    //private Index index;
    @CommitAfter
    Object onSuccess()
    {
        // session.persist(address);
        session.persist(application);
        return null;

    }
    void setup( long productID) {
        this.productID = productID;
        //Product product = (Product) session.createCriteria(Product.class).add(Restrictions.eq("name", "a")).uniqueResult();
        Product product = (Product) session.get(Product.class, productID);
        a[j] =this.productID;
        j++;
        if (product != null) {
            price = product.getPrice();
        }
    }
}
