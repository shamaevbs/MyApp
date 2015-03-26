package MyApp.pages;

import MyApp.entities.Commodity;
import MyApp.entities.Product;
import MyApp.services.WebUser;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 19.02.15
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class Pizza {
    @Inject
    private Session session;
    @Property
    @Persist
    private long productID;

    @Property
    @Persist
    private Long clientID;

    @Property
    private Product product;

    @Inject
    WebUser webUser;

    public List<Product> getProducts()
    {
        return session.createCriteria(Product.class).list();
    }
    void setup()
    {
        productID = 1;

    }

    public List<Commodity> getCommodities() {
        return session.createCriteria(Commodity.class).add(Restrictions.eq("client", webUser.getUser())).list();
    }

    @OnEvent(component = "makeBasket")
     Object makeBasket( long value){
        productID = value;
        if ((session.get(Commodity.class, productID))!=null){
            Commodity commodity =(Commodity) session.get(Commodity.class, productID);
            commodity.amt++;
            Transaction transaction = session.beginTransaction();
            session.save(commodity);
            transaction.commit();
        }
        else {
            Product product = (Product) session.get(Product.class, productID);
            Commodity commodity = new Commodity();
            commodity.name = product.getName();
            commodity.number = product.getNumber();
            commodity.price = product.getPrice();
            commodity.product = productID;
            commodity.client = webUser.getUser();

            commodity.amt = 1;

            Transaction transaction = session.beginTransaction();
            session.save(commodity);
            transaction.commit();

        }




        message = String.format(";");




       // bid.setup(clientID);

        return null;
    }


    @InjectPage
    private Bid bid;
    @Persist(PersistenceConstants.FLASH)
    private String message;

}
