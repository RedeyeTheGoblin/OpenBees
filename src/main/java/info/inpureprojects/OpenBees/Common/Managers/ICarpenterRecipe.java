package info.inpureprojects.OpenBees.Common.Managers;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

/**
 * Created by den on 8/16/2014.
 */
public interface ICarpenterRecipe {

    public boolean doesMatch(Object[] objs, FluidStack fluid);

    public ItemStack getResult();

    public ItemStack getResultCopy();

    public List<ItemStack> getItemsToRemove(IInventory inv);

    public FluidStack getFluidToRemove();

    public int getRecipeDelayInSeconds();


}
