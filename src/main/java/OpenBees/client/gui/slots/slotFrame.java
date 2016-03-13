package OpenBees.client.gui.slots;

import OpenBees.item.interfaces.IFrameItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class slotFrame extends slotCustom {

    public slotFrame(IInventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (stack.getItem() instanceof IFrameItem) {
            return true;
        }

        return false;
    }
}
