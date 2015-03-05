package MyApp.pages;

import MyApp.entities.Application;
import MyApp.entities.Commodity;
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
    @Property @Persist private Commodity pizza;//= new Commodity[10];


    int j;


    //private Index index;
    @CommitAfter
    Object onSuccess()
    {
        // session.persist(address);
        session.persist(application);
        return null;

    }
    void setup( long productID) {
        pizza.id= productID;
        this.productID = productID;
        //Product product = (Product) session.createCriteria(Product.class).add(Restrictions.eq("name", "a")).uniqueResult();
        Product product = (Product) session.get(Product.class, productID);
        j++;
        if (product != null) {
            pizza.price = product.getPrice();
            pizza.name = product.getName();
        }
    }
}
