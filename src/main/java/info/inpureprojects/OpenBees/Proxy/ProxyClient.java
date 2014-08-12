package info.inpureprojects.OpenBees.Proxy;

import com.google.common.eventbus.Subscribe;
import info.inpureprojects.OpenBees.API.Common.Events.EventRegisterIcons;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.API.modInfo;
import info.inpureprojects.OpenBees.Client.CreativeTabBees;
import info.inpureprojects.OpenBees.Client.CreativeTabBlocks;
import info.inpureprojects.OpenBees.Client.EventHandlers.IconManager;
import org.lwjgl.input.Keyboard;

/**
 * Created by den on 8/6/2014.
 */
public class ProxyClient extends ProxyCommon {

    @Override
    public void setupAPI() {
        super.setupAPI();
        OpenBeesAPI.getAPI().getCommonAPI().events.register(this);
        OpenBeesAPI.getAPI().getClientAPI().icons = new IconManager();
        OpenBeesAPI.getAPI().getCommonAPI().events.post(new EventRegisterIcons());
    }

    @Subscribe
    public void setupIcons(EventRegisterIcons evt) {
        // We use the same file names/structures as Forestry for the sake of texture pack creators.
        // It should only require a directory name change to get those packs supporting at least the bees and combs.
        evt.getIconManager().registerIcon(modInfo.modid, "bees/default/body1", "bee_body");
        evt.getIconManager().registerIcon(modInfo.modid, "bees/default/drone.body2", "drone_body");
        evt.getIconManager().registerIcon(modInfo.modid, "bees/default/drone.outline", "drone_outline");
        //
        evt.getIconManager().registerIcon(modInfo.modid, "bees/default/princess.body2", "princess_body");
        evt.getIconManager().registerIcon(modInfo.modid, "bees/default/princess.outline", "princess_outline");
        //
        evt.getIconManager().registerIcon(modInfo.modid, "bees/default/queen.body2", "queen_body");
        evt.getIconManager().registerIcon(modInfo.modid, "bees/default/queen.outline", "queen_outline");
        //
        evt.getIconManager().registerIcon(modInfo.modid, "beeCombs.0", "comb_base");
        evt.getIconManager().registerIcon(modInfo.modid, "beeCombs.1", "comb_overlay");
        //
        evt.getIconManager().registerTexture(modInfo.modid, "apiary.0", "apiary_bottom");
        evt.getIconManager().registerTexture(modInfo.modid, "apiary.1", "apiary_top");
        evt.getIconManager().registerTexture(modInfo.modid, "apiary.4", "apiary_side");
        //
        evt.getIconManager().registerTexture(modInfo.modid, "beehives/beehive.2.top", "beehive_top");
        evt.getIconManager().registerTexture(modInfo.modid, "beehives/beehive.2.side", "beehive_side");
        //
        evt.getIconManager().registerIcon(modInfo.modid, "scoop", "scoop");
        evt.getIconManager().registerIcon(modInfo.modid, "scoop_iron", "scoop_iron");
        evt.getIconManager().registerIcon(modInfo.modid, "scoop_diamond", "scoop_diamond");
        //
        evt.getIconManager().registerTexture(modInfo.modid, "carpenter_side_off", "carpenter_side_off");
        evt.getIconManager().registerTexture(modInfo.modid, "carpenter_side_on", "carpenter_side_on");
        evt.getIconManager().registerTexture(modInfo.modid, "carpenter_top_off", "carpenter_top_off");
        evt.getIconManager().registerTexture(modInfo.modid, "carpenter_top_on", "carpenter_top_on");
        evt.getIconManager().registerTexture(modInfo.modid, "forestfire_machine_bottom", "forestfire_machine_bottom");
        evt.getIconManager().registerTexture(modInfo.modid, "forestfire_machine_side", "forestfire_machine_side");
        evt.getIconManager().registerTexture(modInfo.modid, "forestfire_machine_top", "forestfire_machine_top");
        //
        OpenBeesAPI.getAPI().getClientAPI().creativeTabBees = new CreativeTabBees();
        OpenBeesAPI.getAPI().getClientAPI().creativeTabBlocks = new CreativeTabBlocks();
    }

    @Override
    public boolean isShiftKey() {
        return (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) && !(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL));
    }

    @Override
    public boolean isShiftCtrlKey() {
        return (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) && (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL));
    }

    @Override
    public boolean isCtrlKey() {
        return (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL));
    }
}
