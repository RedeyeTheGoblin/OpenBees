package info.inpureprojects.OpenBees.API.Common.Bees.Genetics;

import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/6/2014.
 */
public class BeeProduct {

    private ItemStack stack;
    private float chance;

    public BeeProduct(ItemStack stack, float chance) {
        this.stack = stack;
        this.chance = chance;
    }

    public ItemStack getStack() {
        return stack;
    }

    public float getChance() {
        return chance;
    }
}
