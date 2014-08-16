package info.inpureprojects.OpenBees.API.Common;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by den on 8/16/2014.
 */
public interface ICarpenterManager {

    public void register(Object[] grid, FluidStack fluid, ItemStack output, int time);

}
