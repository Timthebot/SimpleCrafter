package timthebot.util.random;

import java.awt.*;
import java.util.Random;


/**
 * Created by Tim on 06/07/2015.
 */
public final class Gaussian {

    // Make Gaussian employ singleton design pattern, since all randoms should use this generated with seed once
    private static Gaussian instance = null;
    private Gaussian() {
        rand = new Random(System.currentTimeMillis());
    }
    private Random rand;

    public double getDouble() {
        // Returns uniform random numbers in [0,1)
        return rand.nextDouble();
    }

    public static Gaussian getInstance() {
        if (instance == null) {
            instance = new Gaussian();
        }
        return instance;
    }


    // clone override guarantees only ever 1 instance
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    // END OF SINGLETON PATTERN
    // Usage: Gaussian test = Gaussian.getInstance();


}
