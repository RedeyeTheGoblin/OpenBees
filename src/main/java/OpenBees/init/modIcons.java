package OpenBees.init;

import OpenBees.OpenBees;
import OpenBees.handler.textureHandler;
import OpenBees.info.modInfo;
import OpenBees.utility.logHelper;
import net.minecraft.util.IIcon;

import static OpenBees.info.modInfo.modid;

public class modIcons {

    public static void init()
    {

        OpenBees.coreTexHandler.registerIcon(modid, "bees/default/body1", "bee_body");
        OpenBees.coreTexHandler.registerIcon(modid, "bees/default/drone.body2", "drone_body");
        OpenBees.coreTexHandler.registerIcon(modid, "bees/default/drone.outline", "drone_outline");

        OpenBees.coreTexHandler.registerIcon(modid, "bees/default/princess.body2", "princess_body");
        OpenBees.coreTexHandler.registerIcon(modid, "bees/default/princess.outline", "princess_outline");

        OpenBees.coreTexHandler.registerIcon(modid, "bees/default/queen.body2", "queen_body");
        OpenBees.coreTexHandler.registerIcon(modid, "bees/default/queen.outline", "queen_outline");

        OpenBees.coreTexHandler.registerIcon(modid, "beeCombs.0", "comb_base");
        OpenBees.coreTexHandler.registerIcon(modid, "beeCombs.1", "comb_overlay");

        OpenBees.coreTexHandler.registerTexture(modid, "apiary.0", "apiary_bottom");
        OpenBees.coreTexHandler.registerTexture(modid, "apiary.1", "apiary_top");
        OpenBees.coreTexHandler.registerTexture(modid, "apiary.4", "apiary_side");

        OpenBees.coreTexHandler.registerTexture(modid, "beehives/beehive.2.top", "beehive_top");
        OpenBees.coreTexHandler.registerTexture(modid, "beehives/beehive.2.side", "beehive_side");

        OpenBees.coreTexHandler.registerIcon(modid, "scoop", "scoop");
        OpenBees.coreTexHandler.registerIcon(modid, "scoop_iron", "scoop_iron");
        OpenBees.coreTexHandler.registerIcon(modid, "scoop_diamond", "scoop_diamond");

        OpenBees.coreTexHandler.registerIcon(modid, "tabs/Icon_Energy", "IconEnergy");

    }
}
