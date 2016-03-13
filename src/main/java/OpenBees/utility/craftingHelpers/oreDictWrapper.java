package OpenBees.utility.craftingHelpers;


import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class oreDictWrapper {

    private Object obj;

    public oreDictWrapper(Object obj) {
        this.obj = obj;
    }

    public boolean isItemStack() {
        if (this.isNull()) {
            return false;
        }
        return this.obj instanceof ItemStack;
    }

    public boolean isString() {
        if (this.isNull()) {
            return false;
        }
        return this.obj instanceof String;
    }

    public ItemStack getStack() {
        if (this.isNull()) {
            return null;
        }
        return (ItemStack) this.obj;
    }

    public String getString() {
        if (this.isNull()) {
            return null;
        }
        return this.obj.toString();
    }

    public boolean isNull() {
        return this.obj == null;
    }

    public boolean isEqual(oreDictWrapper two) {
        if (this.isNull() && two.isNull()) {
            return true;
        }
        if (this.isItemStack()) {
            return ItemStack.areItemStacksEqual(this.getStack(), two.getStack());
        }
        if (this.isString()) {
            if (two.isNull()) {
                return false;
            }
            List<ItemStack> list = OreDictionary.getOres(this.getString());
            for (ItemStack i : list) {
                if (OreDictionary.itemMatches(i, two.getStack(), false)) {
                    return true;
                }
            }
        }
        return false;
    }
}
