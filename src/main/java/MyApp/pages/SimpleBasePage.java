package MyApp.pages;

import MyApp.state.Visit;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 19.02.15
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class SimpleBasePage {
    private Visit visit;
    private boolean visitExists;
    protected void setVisit(Visit visit) {
        this.visit = visit;
    }

    public boolean isVisitExists() {
         if (visit==null){
             visitExists= false;
         }
        else{
             visitExists= true;
         }
        return visitExists;
    }
    public Visit getVisit() {
        return visit;
    }
}
