package timthebot.constants.crafting;

import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.script.API;
import timthebot.core.Sleep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TimTheBot
 * 28/01/2016
 */
public class LeatherWork {
    private String name;
    private int requiredLevel;
    private int child;
    private final int parent = 154;
    private final int verifyChild = 88;
    private final String verifyString = "What would you like to make?";

    public static LeatherWork LEATHER_GLOVES = new LeatherWork("Leather gloves", 1, 127);
    public static LeatherWork LEATHER_BOOTS = new LeatherWork("Leather boots", 7, 129);
    public static LeatherWork LEATHER_COWL = new LeatherWork("Leather cowl", 9, 107);
    public static LeatherWork LEATHER_VAMBS = new LeatherWork("Leather vambraces", 11, 131);
    public static LeatherWork LEATHER_BODY = new LeatherWork("Leather body", 14, 125);
    public static LeatherWork LEATHER_CHAPS = new LeatherWork("Leather chaps", 18, 133);
    public static LeatherWork LEATHER_COIF = new LeatherWork("Leather coif", 38, 135);



    public LeatherWork(String name, int requiredLevel, int child) {
        this.name = name;
        this.requiredLevel = requiredLevel;
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public int getChild() {
        return child;
    }

    public int getParent() {
        return parent;
    }

    public static List<LeatherWork> getAllNormalLeatherWork() {
        List<LeatherWork> types = new ArrayList<>();
        types.add(LEATHER_GLOVES);
        types.add(LEATHER_BOOTS);
        types.add(LEATHER_COWL);
        types.add(LEATHER_VAMBS);
        types.add(LEATHER_BODY);
        types.add(LEATHER_CHAPS);
        types.add(LEATHER_GLOVES);
        types.add(LEATHER_COIF);
        return types;
    }

    public static LeatherWork getBestAvailableNormalLeatherWork(API api) {
        List<LeatherWork> candidates = getAllNormalLeatherWork();

        int highestLevel = 0;
        LeatherWork best = null;

        for (LeatherWork candidate : candidates) {
            int req = candidate.getRequiredLevel();

            if (api.getSkills().getStatic(Skill.CRAFTING) >= req && req >= highestLevel) {
                highestLevel = req;
                best = candidate;
            }
        }
        return best;
    }

    public boolean isCraftWindowOpen(API api) {
        RS2Widget craftWindows = api.getWidgets().get(parent, verifyChild);
        if (craftWindows != null) {
            if (craftWindows.getMessage().contains(verifyString)) {
                return true;
            }
        }
        return false;
    }


    public boolean makeAll(API api) {
        if (isCraftWindowOpen(api)) {
            Sleep sleep = new Sleep(10000);

            RS2Widget craftBit = api.getWidgets().get(parent, child);

            RectangleDestination dest = new RectangleDestination(api.getBot(), craftBit.getBounds());
            api.getMouse().move(dest);
            api.getMouse().click(true);
            sleep.sleep();
            double x = api.getMouse().getPosition().getX();
            double y= api.getMouse().getPosition().getY();

            api.getMouse().move((int) x, (int) y + 70);
            api.getMouse().click(false);
            return true;
        } else {
            return false;
        }
    }

}
