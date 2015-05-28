package MyApp.pages;


import MyApp.entities.User;
import MyApp.services.UserAuthent;
import MyApp.state.Visit;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;


//@Secure


public class LogIn  extends SimpleBasePage
{
    @Property
    private String loginId;

    @Property
    private String password;

    @Component(id = "login")
    private Form form;

    @Component(id = "loginId")
    private TextField loginIdField;

    @Persist
    private Link nextPageLink;

    @Inject
    private UserAuthent authent;

    @Inject
    private Logger logger;

    @Property @Persist(PersistenceConstants.FLASH)
    private String testLog, testPas;



    @Inject
    private ComponentResources componentResources;



    //@Override
    public void setNextPageLink(Link nextPageLink) {
        this.nextPageLink = nextPageLink;
    }

    String onPassivate() {
            return loginId;
    }

    void onActivate(String loginId) {
           this.loginId = loginId;
    }

    void onValidateFromLogin() {

            if (form.getHasErrors()) {
                // We get here only if a server-side validator detected an error.

                return;

            }

            try {
                // Authenticate the user
                if (!authent.isValid(loginId, password)) {
                    throw  new BadLogIOrPasException();
                    // record an error, and thereby prevent Tapestry from emitting a "success" event
                }

                User user = authent.authenticateUser(loginId, password);

                // Store the    user in the Visit

                setVisit(new Visit(user));
                //logger.info(user.getLoginId() + " has logged in.");*/
            }
            /*catch (BusinessException e) {
                form.recordError(loginIdField, e.getLocalizedMessage());
            }*/
            /*catch (Exception e) {

                form.recordError("login_problem");
            }*/
            catch (BadLogIOrPasException e ){
                String error ="Invalid user name or password";
                form.recordError(error);
            }

        }
     Object onSuccess() {


         return Pizza.class;


        }

    private class BadLogIOrPasException extends Throwable {
    }

    Object onGoReg(){
       return Registration.class;
    }
}

