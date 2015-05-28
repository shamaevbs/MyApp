package MyApp.components;

import MyApp.pages.LogIn;
import MyApp.state.Visit;
import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;

/**
 * Layout component for pages of application MyApp.
 */
@Import(stylesheet = "context:layout/layout.css")
public class Layout
{
    /**
     * The page title, for the <title> element and the <h1> element.
     */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;


    @Property
    private String pageName;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String sidebarTitle;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private Block sidebar;

    @Inject
    private ComponentResources resources;



    @Property
    @Inject
    @Symbol(SymbolConstants.APPLICATION_VERSION)
    private String appVersion;


    @InjectPage
    LogIn logIn;

    private Visit visit;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String Log;


    public String getClassForPageName()
    {


        return resources.getPageName().equalsIgnoreCase(pageName)
                ? "current_page_item"
                : null;

    }

    public String[] getPageNames()
    {
        return new String[]{"Pizza","News",  "About",  "Book", "Bid"};
    }
    @BeginRender
    void SetLog(){
        if(logIn.isVisitExists()){
            Log="Log Out";
        }else{
            Log= "Log In";
        }
    }



}
