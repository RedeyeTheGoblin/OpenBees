package OpenBees.client;

import OpenBees.OpenBees;
import OpenBees.init.modBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class creativeTabBlocks extends CreativeTabs {

    public creativeTabBlocks()
    {
        super("openbees.blocks");
    }

    @Override
    public Item getTabIconItem()
    {
        return null;
    }

    @Override
    public ItemStack getIconItemStack()
    {
        return OpenBees.blocks.apiary.getStack(1);
    }
}
