package timthebot.core.crafting;

import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.utility.ConditionalSleep;
import timthebot.backend.DefaultAPI;
import timthebot.constants.crafting.CraftItems;
import timthebot.constants.crafting.LeatherWork;
import timthebot.core.Node;

/**
 * Created by TimTheBot
 * 28/01/2016
 */
public class CraftBatch extends Node {

    private CraftItems leatherMaterial;
    private LeatherWork leatherWork;
    private boolean usingBestAvailable;

    public CraftBatch(DefaultAPI api, CraftItems leatherMaterial, boolean useBestAvailable) {
        this(api, leatherMaterial, LeatherWork.getBestAvailableNormalLeatherWork(api));
        usingBestAvailable = true;
    }

    public CraftBatch(DefaultAPI api, CraftItems leatherMaterial, LeatherWork leatherWork) {
        super("Crafting: " + leatherWork.getName(), api);
        this.leatherMaterial = leatherMaterial;
        this.leatherWork = leatherWork;
        this.usingBestAvailable = false;
    }

    @Override
    public boolean validate() {
        getApi().log("Hello");
        boolean leatherInInv = leatherMaterial.isOnPlayer(getApi());
        return (leatherInInv &&
                CraftItems.NEEDLE.isOnPlayer(getApi()) &&
                CraftItems.THREAD.isOnPlayer(getApi()) &&
                !getApi().getBank().isOpen());
    }

    @Override
    public int execute() {
        if (usingBestAvailable) {
            leatherWork = LeatherWork.getBestAvailableNormalLeatherWork(getApi());
        }
        if (leatherWork.isCraftWindowOpen(getApi())) {
            leatherWork.makeAll(getApi());
            int currLevel = getApi().getSkills().getDynamic(Skill.CRAFTING);
            ConditionalSleep sleep = new ConditionalSleep(30000) {
                @Override
                public boolean condition() throws InterruptedException {
                    return !leatherMaterial.isOnPlayer(getApi()) || getApi().getSkills().getStatic(Skill.CRAFTING) > currLevel;
                }
            };
            sleep.sleep();
        } else {
            if (getApi().getInventory().isItemSelected()) {
                getApi().getInventory().interact("Use", leatherMaterial.getId());
            } else {
                getApi().getInventory().interact("Use", CraftItems.NEEDLE.getId());
            }
        }
        return rand(600,1200);
    }

}
