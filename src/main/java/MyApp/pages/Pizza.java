package MyApp.pages;

import MyApp.entities.Commodity;
import MyApp.entities.Product;
import MyApp.services.WebUser;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.util.List;

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

    @Property
    @Persist
    private long ind;

    @Property
    @Persist
    private boolean emptyreq;




    @Persist
    private boolean showInfo;

    @Inject
    private JavaScriptSupport javaScriptSupport;



    public List<Product> getProducts()
    {

        return session.createCriteria(Product.class)
                .list();
    }
    /*
    public Object onActivate() {
        if (true) {
            return logIn;
        } else {
            return null;  // keeps you on this page
        }
    }
    */


    void setShowInfo(boolean show, boolean emptyCom) {
        emptyreq = emptyCom;
        showInfo = show;
    }

    @AfterRender
    void showInfo() {
          if (showInfo) {
              if (emptyreq){
                  javaScriptSupport.addScript("alert('Пустая заявка не может быть принята, пожалуйста, выберите пиццу');");
              }
              else{
                  javaScriptSupport.addScript("alert('Ваша заявка принята!');");
              }
              showInfo = false;
          }
    }


    @OnEvent(component = "makeBasket")
     Object makeBasket( long value){

        productID = value;
        ind = 1;
        List<Commodity> commodityLst = session.createCriteria(Commodity.class)
                .add(Restrictions.eq("product", productID))
                .add(Restrictions.eq("client", webUser.getUser()))
                .add(Restrictions.eq("app", ind))
                .list();
        if (!commodityLst.isEmpty()  ){
            Transaction transaction = session.beginTransaction();
            Commodity commodity = commodityLst.get(0);
            commodity.amt++;

            session.save(commodity);
            transaction.commit();
        }
        else {
            Product product = (Product) session.get(Product.class, productID);
            Commodity commodity = new Commodity();
            commodity.name = product.getName();
            //commodity.number = product.getNumber();
            commodity.price = product.getPrice();
            commodity.product = productID;
            commodity.client = webUser.getUser();
            commodity.app = 1;
            commodity.amt = 1;

            Transaction transaction = session.beginTransaction();
            session.save(commodity);
            transaction.commit();
        }

        commodityLst = session.createCriteria(Commodity.class)
                .add(Restrictions.eq("client", webUser.getUser()))
                .list();
        BigDecimal total;
        /*for(Commodity commodity : commodityLst) {
            total += Long.valueOf(commodity.price) * commodity.amt;
        }*/
        BigDecimal total1 = new BigDecimal(0.0);
        for(Commodity commodity : commodityLst) {
            BigDecimal amt= new BigDecimal(commodity.amt);
            total1 = total1.add(commodity.price.multiply(amt));
            //cost1 += commodity.price * commodity.amt;
        }
        total=total1.setScale(2,BigDecimal.ROUND_FLOOR);
        message = String.format(";");



        return null;
    }


    @InjectPage
    private Bid bid;
    @Persist(PersistenceConstants.FLASH)
    private String message;

}
