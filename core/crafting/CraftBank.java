package timthebot.core.crafting;

import org.osbot.rs07.utility.ConditionalSleep;
import timthebot.backend.DefaultAPI;
import timthebot.constants.crafting.CraftItems;
import timthebot.core.Node;
import timthebot.core.Sleep;

/**
 * Created by TimTheBot
 * 29/01/2016
 */
public class CraftBank extends Node {
    private CraftItems leather;

    public CraftBank(DefaultAPI api, CraftItems leather) {
        super("Getting more leather!", api);
        this.leather = leather;
    }

    @Override
    public boolean validate() {
        return !leather.isOnPlayer(getApi());
    }

    @Override
    public int execute() {
        if (getApi().getBank().isOpen()) {
            getApi().getBank().depositAllExcept(CraftItems.NEEDLE.getId(), CraftItems.THREAD.getId());

            Sleep sleep = new Sleep(1200);
            sleep.sleep();

            getApi().getBank().withdraw(leather.getId(), 32);

            ConditionalSleep sleep1 = new ConditionalSleep(3000) {
                @Override
                public boolean condition() throws InterruptedException {
                    return getApi().getInventory().contains(leather.getId());
                }
            };

            getApi().getBank().close();

        } else {
            try {
                getApi().getBank().open();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        return rand(600, 1200);
    }
}
