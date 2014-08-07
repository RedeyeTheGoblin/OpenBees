package info.inpureprojects.OpenBees.Client;

import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/7/2014.
 */
public class CreativeTabBlocks extends CreativeTabs {

    public CreativeTabBlocks() {
        super("openbees.blocks");
    }

    @Override
    public Item getTabIconItem() {
        return null;
    }

    @Override
    public ItemStack getIconItemStack() {
        return OpenBeesAPI.getAPI().getCommonAPI().blocks.apiary.getStack(1);
    }
}
