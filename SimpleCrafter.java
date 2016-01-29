package timthebot;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import timthebot.backend.DefaultAPI;
import timthebot.constants.crafting.CraftItems;
import timthebot.core.NodeMaster;
import timthebot.core.crafting.LeatherCrafter;

import java.awt.*;

/**
 * Created by TimTheBot
 * 26/01/2016
 */

@ScriptManifest(
        info = "Crafts best leather item available, must have Thread, Needle in inventory, Leather in bank",
        name = "SimpleCrafter",
        logo = "",
        author =  "Timthebot",
        version = 0.1
)
public class SimpleCrafter extends Script {
    NodeMaster script;

    @Override
    @SuppressWarnings("deprecation")
    public void onStart() {
        //Code here will execute before the loop is started
        DefaultAPI api = new DefaultAPI();
        api.exchangeContext(getBot());

        script = new LeatherCrafter(api, CraftItems.LEATHER, null);
    }

    @Override
    public void onExit() {
        log("Thank you for using my bot!");
    }


    @Override
    public int onLoop() {
        if (script.validate()) {
            return script.execute();
        }
        return 100; //The amount of time in milliseconds before the loop starts over
    }


    @Override
    public void onPaint(Graphics2D g) {
        //This is where you will put your code for paint(s)

    }
}
