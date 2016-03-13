package OpenBees.client.gui.slots;

import OpenBees.OpenBees;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class slotRoyal extends slotCustom {

    public slotRoyal(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return OpenBees.coreBeeHelper.isQueen(stack) || OpenBees.coreBeeHelper.isPrincess(stack);

    }
}
