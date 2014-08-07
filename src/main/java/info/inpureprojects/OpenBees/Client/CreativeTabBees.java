package info.inpureprojects.OpenBees.Client;

import info.inpureprojects.OpenBees.API.Common.Bees.IBeeManager;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/6/2014.
 */
public class CreativeTabBees extends CreativeTabs {

    public CreativeTabBees() {
        super("openbees.bees");
    }

    @Override
    public Item getTabIconItem() {
        return null;
    }

    @Override
    public ItemStack getIconItemStack() {
        return OpenBeesAPI.getAPI().getCommonAPI().beeManager.getBeeBySpeciesTag("Forest", IBeeManager.Type.QUEEN);
    }
}
