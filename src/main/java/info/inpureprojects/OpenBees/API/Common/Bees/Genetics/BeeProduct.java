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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BeeProduct)) return false;

        BeeProduct that = (BeeProduct) o;

        if (Float.compare(that.chance, chance) != 0) return false;
        if (!stack.isItemEqual(that.stack)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stack.hashCode();
        result = 31 * result + (chance != +0.0f ? Float.floatToIntBits(chance) : 0);
        return result;
    }
}
