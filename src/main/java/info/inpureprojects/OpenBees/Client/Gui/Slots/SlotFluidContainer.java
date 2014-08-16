package info.inpureprojects.OpenBees.Client.Gui.Slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

/**
 * Created by den on 8/13/2014.
 */
public class SlotFluidContainer extends SlotCustom {

    public SlotFluidContainer(IInventory inv, int index, int x, int y) {
        super(inv, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (stack == null) {
            return true;
        }
        return FluidContainerRegistry.isFilledContainer(stack);
    }
}
