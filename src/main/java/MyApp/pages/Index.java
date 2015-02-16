package MyApp.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Log;

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
}
