package MyApp.pages;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 12.02.15
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
public class Guess {
    @Property
    @Persist
        private int target, guessCount ;

    @Property
    private int current;

    void setup(int target)
    {
        this.target = target;
        guessCount = 1;

    }

    @OnEvent(component = "makeGuess")
    Object makeGuess(int value)
    {
        if (value == target)
        {
            gameOver.setup(target, guessCount);
            return gameOver;


        }
        guessCount++;
        message = String.format("Your guess of %d is too %s.", value, value < target ? "low" : "high");
        return null;
    }

    @Property

    @Persist(PersistenceConstants.FLASH)
    private String message;
    @InjectPage
    private GameOver gameOver;


}
