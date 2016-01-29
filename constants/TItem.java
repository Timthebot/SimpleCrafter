package timthebot.constants;

import org.osbot.rs07.script.API;

/**
 * Created by TimTheBot
 * 28/01/2016
 */
public abstract class TItem {

    public int itemId;
    public int getId() {return itemId;}

    public boolean isOnPlayer(API api) {
        return api.getInventory().contains(getId()) || api.getEquipment().contains(getId());
    }
}
