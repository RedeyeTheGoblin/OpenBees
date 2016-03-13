package OpenBees.client.gui.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;


public class slotInput extends slotCustom {

    public slotInput(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return true;
    }
}

