package MyApp.pages;

import MyApp.entities.Application;
import MyApp.entities.Commodity;
import MyApp.services.WebUser;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.upload.services.UploadedFile;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
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

    @Property @Persist private BigDecimal cost;
    @Property @Persist private long ind;
    @Property @Persist private boolean emptycom;

    @Property @Persist private long clientID;
    @Property @Persist private String name;
    @InjectPage
    private Pizza mainPage;


    @Property
    private UploadedFile file;

    @Inject
    WebUser webUser;

    @CommitAfter
    Object onSuccess()    {

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
        //new cost1 = '0'
        BigDecimal cost1 = new BigDecimal(0.0);
        for(Commodity commodity : commodityLst) {
            BigDecimal amt= new BigDecimal(commodity.amt);
            cost1 = cost1.add(commodity.price.multiply(amt));
            //cost1 += commodity.price * commodity.amt;
        }
        cost=cost1.setScale(2,BigDecimal.ROUND_FLOOR);
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


}
