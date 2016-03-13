package OpenBees.client.gui.slots;

import OpenBees.item.itemComb;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class slotComb extends slotCustom {

    public slotComb(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (stack.getItem() instanceof itemComb) {
            return true;
        }

        return false;
    }
}
