package timthebot.core;

import org.osbot.rs07.utility.ConditionalSleep;
import timthebot.util.random.Gaussian;

/**
 * Created by TimTheBot
 * 29/01/2016
 */
public class Sleep extends ConditionalSleep {

    public int runTime;
    public int timeoutTime;

    public Sleep(int timeout) {
        super(timeout);
        this.timeoutTime = timeout;
        this.runTime = 0;
    }

    public void reset() {
        this.runTime = 0;
    }

    @Override
    public boolean condition() throws InterruptedException {
        this.runTime += 50;
        wait(50);
        double randomNumber = Gaussian.getInstance().getDouble();
        if (randomNumber < Math.exp(runTime * Math.log(2) / timeoutTime)) {
            return true;
        }
        return true;
    }
}
