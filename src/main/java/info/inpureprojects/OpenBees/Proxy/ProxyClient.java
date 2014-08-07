package info.inpureprojects.OpenBees.Proxy;

import com.google.common.eventbus.Subscribe;
import info.inpureprojects.OpenBees.API.Common.Events.EventRegisterIcons;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.API.modInfo;
import info.inpureprojects.OpenBees.Client.CreativeTabBees;
import info.inpureprojects.OpenBees.Client.CreativeTabBlocks;
import info.inpureprojects.OpenBees.Client.CreativeTabCombs;
import info.inpureprojects.OpenBees.Client.EventHandlers.IconManager;

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
        OpenBeesAPI.getAPI().getClientAPI().creativeTabBees = new CreativeTabBees();
        OpenBeesAPI.getAPI().getClientAPI().creativeTabCombs = new CreativeTabCombs();
        OpenBeesAPI.getAPI().getClientAPI().creativeTabBlocks = new CreativeTabBlocks();
    }
}
