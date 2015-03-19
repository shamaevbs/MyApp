package MyApp.pages;

import MyApp.entities.Application;
import MyApp.entities.Commodity;
import MyApp.services.WebUser;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class Bid
{
    @Property private Application application;
    @Property private Commodity commodity;
    @Inject private Session session;

    @Component(id = "applicationform")
    private Form form;

    @Persist(PersistenceConstants.FLASH)
    private String message;

    @Property @Persist private long clientID;
    @Property @Persist private String name;


    @Inject
    WebUser webUser;

    @CommitAfter
    Object onSuccess()
    {
        session.persist(application);
        return null;

    }

    public List<Commodity> getCommodities() {
        return session.createCriteria(Commodity.class).add(Restrictions.eq("client", webUser.getUser())).list();
    }


    @OnEvent(component = "delete")
    Object Delete( long value){


        //Product product = (Product) session.get(Product.class, productID);
        Commodity commodity =(Commodity) session.load(Commodity.class, value);
        Transaction transaction = session.beginTransaction();
        session.delete(commodity);
        transaction.commit();
        return null;
    }





    void setup(long clientID) {
        //this.clientID = clientID;
       /* //Product product = (Product) session.createCriteria(Product.class).add(Restrictions.eq("name", "a")).uniqueResult();
        Product product = (Product) session.get(Product.class, productID);

        //commodity= (Commodity) session.merge(product);
        //session.persist(commodity);
        if (product != null) {
            //commodity.name = product.getName();
            //commodity.number = product.getNumber();
            //commodity.price = product.getPrice();
            //commodity.id = product.getId();
            //commodity[1].price = product.getPrice();
            if (message==null){
                message ="";
            }
            message = String.format(message+" "+product.getPrice()+ " "+  product.getName()+"," );
            //message= String.valueOf(0);
        }*/
    }

}
