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
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class Bid
{
    @Property private Application application;
    @Property private Commodity commodity;
    @Inject private Session session;

    @Component(id = "applicationform")
    private Form form;



    //@InjectPage
    @Property @Persist private long clientID;
    //@Property @Persist private Commodity commodity;
    @Property @Persist private String name;


    /*@Component(id = "personform")
    private Form form;*/

    @Inject
    WebUser webUser;

    @Property @Persist(PersistenceConstants.FLASH)
    private String message;

    //private Index index;
    @CommitAfter
    Object onSuccess()
    {
        session.persist(application);
        return null;


        //message = String.format(message+" "+product.getPrice()+ " "+  product.getName()+"," );
    }



    public List<Commodity> getCommodities() {
        return session.createCriteria(Commodity.class).add(Restrictions.eq("client", webUser.getUser())).list();
    }

    @OnEvent(component = "applicationform")
    Object makeGuess()
    {

        message = String.format(";");
        return null;
    }

    void setup(long clientID) {
        message= String.format(";");
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
