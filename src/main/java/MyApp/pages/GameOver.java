package MyApp.pages;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
/**
 * Created with IntelliJ IDEA.
 * User: shamaev.bs
 * Date: 12.02.15
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class GameOver {
    @Property
    @Persist
    private int target, guessCount;
    void setup(int target, int guessCount)
    {
        this.target = target;
        this.guessCount = guessCount;

    }
}
