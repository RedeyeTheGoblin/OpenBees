package OpenBees.init;

import OpenBees.OpenBees;
import OpenBees.info.modInfo;
import OpenBees.item.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

@GameRegistry.ObjectHolder(modInfo.modid)
public class modItems {

    public static void init()
    {
        //Bees
        OpenBees.items.drone = new itemWrapper(new itemBee("openbees.bee.drone", 64, "drone"), 0);
        OpenBees.items.princess = new itemWrapper(new itemBee("openbees.bee.princess", 1, "princess"), 0);
        OpenBees.items.queen = new itemWrapper(new itemBee("openbees.bee.queen", 1, "queen"), 0);

        //Combs
        OpenBees.items.base_comb = new itemComb("openbees.comb", 64);
        OpenBees.items.honey_comb = new itemWrapper( OpenBees.items.base_comb, 0);
        /*OpenBees.items.cocoa_comb = new itemWrapper( OpenBees.items.base_comb, 1);
        OpenBees.items.parched_comb = new itemWrapper( OpenBees.items.base_comb, 2);
        OpenBees.items.frozen_comb = new itemWrapper( OpenBees.items.base_comb, 3);
        OpenBees.items.silky_comb = new itemWrapper( OpenBees.items.base_comb, 4);*/

        //Tools
        OpenBees.items.scoop_wood = new itemWrapper(new itemScoop("openbees.scoop.0", Item.ToolMaterial.WOOD, "scoop"), 0);
        OpenBees.items.scoop_iron = new itemWrapper(new itemScoop("openbees.scoop.1", Item.ToolMaterial.IRON, "scoop_iron"), 0);
        OpenBees.items.scoop_diamond = new itemWrapper(new itemScoop("openbees.scoop.2", Item.ToolMaterial.EMERALD, "scoop_diamond"), 0);

    }
}
