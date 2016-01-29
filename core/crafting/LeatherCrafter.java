package timthebot.core.crafting;

import timthebot.backend.DefaultAPI;
import timthebot.constants.crafting.CraftItems;
import timthebot.constants.crafting.LeatherWork;
import timthebot.core.NodeMaster;

/**
 * Created by TimTheBot
 * 28/01/2016
 */
public class LeatherCrafter extends NodeMaster {
    public LeatherCrafter(DefaultAPI api, CraftItems leatherType, LeatherWork job) {
        // leatherType, currently only normal leather supported, sorry
        // job -> If null, will pick best

        if (job == null) {
            add(new CraftBatch(api, leatherType, true));
        } else {
            add(new CraftBatch(api, leatherType, job));
        }
        add(new CraftBank(api, leatherType));
        add(new BankCloser(api, leatherType));

        api.log("Loaded LeatherCrafter!");

    }


    @Override
    public boolean validate() {
        return true;
    }
}
