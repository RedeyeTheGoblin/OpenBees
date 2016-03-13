package OpenBees.client.gui.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class slotFluidContainer extends slotCustom {

    public slotFluidContainer(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (stack == null) {
            return true;
        }
        return FluidContainerRegistry.isFilledContainer(stack);
    }

}
