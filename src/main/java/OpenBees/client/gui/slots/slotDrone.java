package OpenBees.client.gui.slots;

import OpenBees.OpenBees;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class slotDrone extends slotCustom {

    public slotDrone(IInventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        if (OpenBees.coreBeeHelper.isDrone(stack))
        {
            return true;
        }

        return false;
    }

}
