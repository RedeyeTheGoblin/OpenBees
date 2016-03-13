package OpenBees.genetics;

import net.minecraft.item.ItemStack;

public class beeProduct {

    private ItemStack stack;
    private float chance;

    public beeProduct(ItemStack stack, float chance) {
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof beeProduct)) return false;

        beeProduct that = (beeProduct) obj;

        if(Float.compare(that.chance, chance) != 0) return false;
        if(!stack.isItemEqual(that.stack)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stack.hashCode();
        result = 31 * result + (chance != +0.0f ? Float.floatToIntBits(chance) : 0);
        return result;
    }
}
