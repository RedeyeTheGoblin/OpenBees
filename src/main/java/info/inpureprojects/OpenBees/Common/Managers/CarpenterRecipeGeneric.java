package info.inpureprojects.OpenBees.Common.Managers;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by den on 8/16/2014.
 */
public class CarpenterRecipeGeneric implements ICarpenterRecipe {

    public ItemStack topLeft;
    public ItemStack topMiddle;
    public ItemStack topRight;
    //
    public ItemStack middleLeft;
    public ItemStack middleMiddle;
    public ItemStack middleRight;
    //
    public ItemStack bottomLeft;
    public ItemStack bottomMiddle;
    public ItemStack bottomRight;
    //
    public FluidStack fluid;
    //
    public ItemStack output;
    //
    private int delay;

    public CarpenterRecipeGeneric(Object[] grid, FluidStack fluid, ItemStack output, int delay) {
        topLeft = (ItemStack) grid[0];
        topMiddle = (ItemStack) grid[1];
        topRight = (ItemStack) grid[2];
        //
        middleLeft = (ItemStack) grid[3];
        middleMiddle = (ItemStack) grid[4];
        middleRight = (ItemStack) grid[5];
        //
        bottomLeft = (ItemStack) grid[6];
        bottomMiddle = (ItemStack) grid[7];
        bottomRight = (ItemStack) grid[8];
        //
        this.fluid = fluid;
        //
        this.output = output;
        this.delay = delay;
    }

    @Override
    public int getRecipeDelayInSeconds() {
        return this.delay;
    }

    public boolean areMatch(ItemStack one, ItemStack two) {
        boolean match = false;
        if (one == null && two == null) {
            match = true;
        } else {
            match = ItemStack.areItemStacksEqual(one, two);
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
        return this.getResult().copy();
    }

    @Override
    public List<ItemStack> getItemsToRemove(IInventory inv) {
        ArrayList<ItemStack> items = new ArrayList();
        try {
            for (Field f : this.getClass().getDeclaredFields()) {
                if (f.getType().equals(ItemStack.class) && !f.getName().equals("output")) {
                    ItemStack i = (ItemStack) f.get(this);
                    items.add(i);
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
}
