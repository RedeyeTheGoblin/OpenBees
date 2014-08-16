package info.inpureprojects.OpenBees.Common.Managers;

import info.inpureprojects.OpenBees.Client.Gui.ContainerCarpenter;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by den on 8/16/2014.
 */
public class CarpenterRecipeOreDict implements ICarpenterRecipe {

    public OreDictWrapper topLeft;
    public OreDictWrapper topMiddle;
    public OreDictWrapper topRight;
    //
    public OreDictWrapper middleLeft;
    public OreDictWrapper middleMiddle;
    public OreDictWrapper middleRight;
    //
    public OreDictWrapper bottomLeft;
    public OreDictWrapper bottomMiddle;
    public OreDictWrapper bottomRight;
    //
    public FluidStack fluid;
    //
    public ItemStack output;
    //
    private int delay;

    public CarpenterRecipeOreDict(Object[] grid, FluidStack fluid, ItemStack output, int delay) {
        topLeft = new OreDictWrapper(grid[0]);
        topMiddle = new OreDictWrapper(grid[1]);
        topRight = new OreDictWrapper(grid[2]);
        //
        middleLeft = new OreDictWrapper(grid[3]);
        middleMiddle = new OreDictWrapper(grid[4]);
        middleRight = new OreDictWrapper(grid[5]);
        //
        bottomLeft = new OreDictWrapper(grid[6]);
        bottomMiddle = new OreDictWrapper(grid[7]);
        bottomRight = new OreDictWrapper(grid[8]);
        //
        this.fluid = fluid;
        //
        this.output = output;
        this.delay = delay;
    }

    public boolean areMatch(OreDictWrapper one, ItemStack two) {
        boolean match = false;
        if (one == null && two == null) {
            match = true;
        } else {
            match = one.isEqual(new OreDictWrapper(two));
        }
        return match;
    }

    public boolean fluidValid(FluidStack fluid) {
        if (this.fluid == null) {
            return true;
        }
        return fluid.containsFluid(this.fluid);
    }

    public boolean isEqual(CarpenterRecipeGeneric recipe) {
        return areMatch(topLeft, recipe.topLeft) && areMatch(topMiddle, recipe.topMiddle) && areMatch(topRight, recipe.topRight) && areMatch(middleLeft, recipe.middleLeft) &&
                areMatch(middleMiddle, recipe.middleMiddle) && areMatch(middleRight, recipe.middleRight) && areMatch(bottomLeft, recipe.bottomLeft) && areMatch(bottomMiddle, recipe.bottomMiddle) &&
                areMatch(bottomRight, recipe.bottomRight) && this.fluidValid(recipe.fluid);
    }

    @Override
    public boolean doesMatch(Object[] objs, FluidStack fluid) {
        return isEqual(new CarpenterRecipeGeneric(objs, fluid, null, 1));
    }

    @Override
    public ItemStack getResult() {
        return this.output;
    }

    @Override
    public ItemStack getResultCopy() {
        return this.output.copy();
    }

    // TODO: Find a better way to do this. Fucking nested loops everywhere.
    @Override
    public List<ItemStack> getItemsToRemove(IInventory inv) {
        ArrayList<ItemStack> items = new ArrayList();
        try {
            for (Field f : this.getClass().getDeclaredFields()) {
                if (f.getType().equals(OreDictWrapper.class)) {
                    OreDictWrapper i = (OreDictWrapper) f.get(this);
                    if (i.isNull()) {
                        items.add(null);
                        continue;
                    }
                    if (i.isItemStack()) {
                        items.add(i.getStack());
                    } else if (i.isString()) {
                        List<ItemStack> list = OreDictionary.getOres(i.getString());
                        outerLoop:
                        for (Integer index : ContainerCarpenter.inputSlots) {
                            for (ItemStack stack : list) {
                                if (OreDictionary.itemMatches(stack, inv.getStackInSlot(index), false)) {
                                    items.add(stack);
                                    break outerLoop;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return Collections.unmodifiableList(items);
    }

    @Override
    public FluidStack getFluidToRemove() {
        return this.fluid;
    }

    @Override
    public int getRecipeDelayInSeconds() {
        return this.delay;
    }

    public static class OreDictWrapper {

        private Object obj;

        public OreDictWrapper(Object obj) {
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

        public boolean isEqual(OreDictWrapper two) {
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
}
