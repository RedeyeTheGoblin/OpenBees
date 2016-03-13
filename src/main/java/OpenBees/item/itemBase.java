package OpenBees.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public abstract class itemBase extends Item {

    public itemBase(String unloc, int maxStack) {
        this.setUnlocalizedName(unloc);
        this.setMaxStackSize(maxStack);
    }

    @Override
    public abstract IIcon getIcon(ItemStack stack, int pass);

    /*@Override
    public abstract void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool);
    */
    @Override
    public abstract void getSubItems(Item item, CreativeTabs tabs, List list);

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public abstract int getRenderPasses(int metadata);
}
