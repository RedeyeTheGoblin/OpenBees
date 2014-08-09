package info.inpureprojects.OpenBees.API.Common.Bees.Genetics;

import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/6/2014.
 */
public class BeeProduct {

    private ItemStack stack;
    private int chance;

    public BeeProduct(ItemStack stack, int chance) {
        this.stack = stack;
        this.chance = chance;
    }

    public ItemStack getStack() {
        return stack;
    }

    public int getChance() {
        return chance;
    }
}
