package info.inpureprojects.OpenBees.API.Common;

import cpw.mods.fml.common.registry.GameRegistry;
import info.inpureprojects.OpenBees.API.modInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/6/2014.
 */
public class ItemWrapper {

    private ItemStack stack;

    public ItemWrapper(Item item, int meta) {
        this.stack = new ItemStack(item, 1, meta);
        GameRegistry.registerItem(item, item.getUnlocalizedName(), modInfo.modid);
    }

    public ItemStack getStack(int quantity) {
        ItemStack copy = this.stack.copy();
        copy.stackSize = quantity;
        return copy;
    }

    public Item getItem() {
        return stack.getItem();
    }
}
