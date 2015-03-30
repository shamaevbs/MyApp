package MyApp.pages;

import MyApp.entities.Application;
import MyApp.entities.Commodity;
import MyApp.services.WebUser;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.*;
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

    @Property @Persist private long cost;
    @Property @Persist private long ind;
    @Property @Persist private boolean emptycom;

    @Property @Persist private long clientID;
    @Property @Persist private String name;
    @InjectPage
    private Pizza mainPage;


    @Inject
    WebUser webUser;

    @CommitAfter
    Object onSuccess()
    {
        session.persist(application);
        List<Commodity> commodityLst = session.createCriteria(Commodity.class)
                .add(Restrictions.eq("client", webUser.getUser()))
                .add(Restrictions.eq("app", ind  ))
                .list();
        for(Commodity commodity : commodityLst) {
            //cost += Long.valueOf(commodity.price) * commodity.amt;
            commodity.app = application.id;
        }

        if(commodityLst.isEmpty()){
            emptycom = true;
        }
        else{
            emptycom = false;
        }
        mainPage.setShowInfo(true, emptycom);
        return mainPage;

    }

    Object onValidateFromApplicationform() {
         return true;
    }

    Object onFailure() {
        return null;

    }

    public List<Commodity> getCommodities() {

        List<Commodity> commodityLst = session.createCriteria(Commodity.class)
                .add(Restrictions.eq("client", webUser.getUser()))
                .add(Restrictions.eq("app", ind  ))
                .list();
        cost= 0;
        for(Commodity commodity : commodityLst) {
            cost += Long.valueOf(commodity.price) * commodity.amt;
        }
        ind= 1;
        return session.createCriteria(Commodity.class)
                .add(Restrictions.eq("client", webUser.getUser()))
                .add(Restrictions.eq("app", ind  ))
                .list();
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

    @OnEvent(component = "addition")
    Object Addition( long value){


        //Product product = (Product) session.get(Product.class, productID);
        Commodity commodity =(Commodity) session.get(Commodity.class, value);
        Transaction transaction = session.beginTransaction();

        commodity.amt++;
        session.save(commodity);
        transaction.commit();
        return null;
    }

    @OnEvent(component = "decrease")
    Object Decrease ( long value){


        //Product product = (Product) session.get(Product.class, productID);
        Commodity commodity =(Commodity) session.get(Commodity.class, value);
        Transaction transaction = session.beginTransaction();
        if(commodity.amt>1){
            commodity.amt--;
            session.save(commodity);
        }
        else if(commodity.amt==1){
            session.delete(commodity);
        }

        transaction.commit();
        return null;
    }




        void setup() {
        }

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
