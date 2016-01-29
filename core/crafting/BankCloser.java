package timthebot.core.crafting;

import timthebot.backend.DefaultAPI;
import timthebot.constants.crafting.CraftItems;
import timthebot.core.Node;

/**
 * Created by TimTheBot
 * 29/01/2016
 */
public class BankCloser extends Node {

    CraftItems leather;

    public BankCloser(DefaultAPI api, CraftItems leather) {
        super("Closing bank", api);
        this.leather = leather;
    }

    @Override
    public boolean validate() {
        return getApi().getInventory().contains(leather.getId())
                && getApi().getBank().isOpen();
    }

    @Override
    public int execute() {
        getApi().getBank().close();
        return rand(600, 1200);
    }
}
