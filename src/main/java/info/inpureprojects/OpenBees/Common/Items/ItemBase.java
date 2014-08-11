package info.inpureprojects.OpenBees.Common.Items;

import info.inpureprojects.OpenBees.Common.NeedsMovedToCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by den on 8/6/2014.
 */
@NeedsMovedToCore
public abstract class ItemBase extends Item {

    public ItemBase(String unloc, int maxStack) {
        this.setUnlocalizedName(unloc);
        this.setMaxStackSize(maxStack);
    }

    @Override
    public abstract IIcon getIcon(ItemStack stack, int pass);

    @Override
    public abstract void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool);

    @Override
    public abstract void getSubItems(Item item, CreativeTabs tabs, List list);

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public abstract int getRenderPasses(int metadata);
}
