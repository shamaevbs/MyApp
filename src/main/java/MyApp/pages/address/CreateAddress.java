package MyApp.pages.address;

import MyApp.entities.Address;
import MyApp.pages.Index;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 12.02.15
 * Time: 18:18
 * To change this template use File | Settings | File Templates.
 */
public class CreateAddress {
    @Property
    private Address address;
    @Inject
    private Session session;
    @InjectPage
    private Index index;
    @CommitAfter
    Object onSuccess()
    {
        session.persist(address);

        return index;
    }

}
