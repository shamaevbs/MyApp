package MyApp.pages;


import MyApp.services.UserAuthent;
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


public class LogIn
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
                if (!authent.valid(loginId, password)) {
                    // record an error, and thereby prevent Tapestry from emitting a "success" event
                    form.recordError("Invalid user name or password.");
                }
                /*User user = securityFinderService.authenticateUser(loginId, password);

                // Store the user in the Visit

                setVisit(new Visit(user));
                logger.info(user.getLoginId() + " has logged in.");*/
            }
            /*catch (BusinessException e) {
                form.recordError(loginIdField, e.getLocalizedMessage());
            }*/
            catch (Exception e) {
                /*logger.error("Could not log in.  Stack trace follows...");
                logger.error(ExceptionUtil.printStackTrace(e));*/
                form.recordError("login_problem");
            }

        }
     Object onSuccess() {
        logger.error("Error on Succes");
        Class ret;

            if (nextPageLink == null) {
                //loginId= String.valueOf(loginIdField);
                if(loginId.compareTo("admin")==0){
                    ret =Index.class;

                }
                else{

                    ret= Pizza.class;
                }

            }
            else {
                componentResources.discardPersistentFieldChanges();
                //return nextPageLink;
                ret= Index.class;
            }
         return LogIn.class;


        }



}