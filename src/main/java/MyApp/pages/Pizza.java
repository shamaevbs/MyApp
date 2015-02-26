package MyApp.pages;

import MyApp.entities.Product;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

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
    private Product product;

    public List<Product> getProducts()
    {
        return session.createCriteria(Product.class).list();
    }
}
