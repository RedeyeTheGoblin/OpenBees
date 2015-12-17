package OpenBees.client.gui.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class slotCustom extends Slot {

    public slotCustom(IInventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
    }

    @Override
    public abstract boolean isItemValid(ItemStack stack);
}
