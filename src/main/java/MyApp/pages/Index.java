package MyApp.pages;

import MyApp.entities.Address;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.List;
import java.util.Random;

/**
 * Start page of application MyApp.
 */

public class Index
{
    private final Random random = new Random(System.nanoTime());
    @InjectPage
    private Guess guess;

    @Log
    Object onActionFromStart()
    {
        int target = random.nextInt(10) + 1;
        guess.setup(target);
        return guess;
    }
    @Inject
    private Session session;
    public List<Address> getAddresses()
    {
        return session.createCriteria(Address.class).list();
    }
}
