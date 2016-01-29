package timthebot.constants.crafting;

import timthebot.constants.TItem;

/**
 * Created by TimTheBot
 * 28/01/2016
 */
public class CraftItems extends TItem {

    public static CraftItems NEEDLE = new CraftItems(1733, "Needle");
    public static CraftItems THREAD = new CraftItems(1734, "Thread");
    public static CraftItems LEATHER = new CraftItems(1741, "Leather");

    private String name;

    public CraftItems(int id, String name) {
        this.itemId = id;
        this.name = name;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
