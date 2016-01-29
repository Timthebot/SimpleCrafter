package timthebot.core;

import timthebot.backend.DefaultAPI;
import timthebot.util.random.Gaussian;

/**
 * Created by TimTheBot
 * 26/01/2016
 */
public abstract class Node {
    private String status;
    private DefaultAPI api;

    public Node(String status, DefaultAPI api) {
        this.status = status;
        this.api = api;
    }

    public DefaultAPI getApi() {
        return api;
    }

    public String getStatus() {
        return status;
    }

    public abstract boolean validate();
    public abstract int execute();

    public void initialise(){

    }


    public double rand() {
        // returns uniform random numbers in interval [0,1)

        Gaussian g = Gaussian.getInstance();
        return g.getDouble();
    }

    public int rand(int lower, int upper) {
        // returns uniform random numbers in interval [lower, upper)
        int delta = upper - lower;

        int randomValDelta = (int) (delta * rand());
        return randomValDelta + lower;
    }

}
