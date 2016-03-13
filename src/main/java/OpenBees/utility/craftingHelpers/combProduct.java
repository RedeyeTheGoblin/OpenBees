package OpenBees.utility.craftingHelpers;

import net.minecraft.item.ItemStack;

public class combProduct {

    private ItemStack stack;
    private float chance;

    public combProduct(ItemStack stack, float chance) {
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
        if (!(obj instanceof combProduct)) return false;

        combProduct that = (combProduct) obj;

        if(Float.compare(that.chance, chance) != 0) return false;
        if(!stack.isItemEqual(that.stack)) return false;

        return true;
    }
}
