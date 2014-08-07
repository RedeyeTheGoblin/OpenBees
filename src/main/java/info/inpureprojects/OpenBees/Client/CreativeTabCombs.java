package info.inpureprojects.OpenBees.Client;

import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/6/2014.
 */
public class CreativeTabCombs extends CreativeTabs {

    public CreativeTabCombs() {
        super("openbees.combs");
    }

    @Override
    public Item getTabIconItem() {
        return null;
    }

    @Override
    public ItemStack getIconItemStack() {
        return OpenBeesAPI.getAPI().getCommonAPI().items.honey_comb.getStack(1);
    }
}
