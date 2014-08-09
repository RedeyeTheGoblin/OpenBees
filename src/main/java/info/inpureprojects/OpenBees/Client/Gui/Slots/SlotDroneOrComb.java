package info.inpureprojects.OpenBees.Client.Gui.Slots;

import info.inpureprojects.OpenBees.API.Common.Bees.CombItem;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/8/2014.
 */
public class SlotDroneOrComb extends SlotCustom{
    public SlotDroneOrComb(IInventory inv, int index, int x, int y) {
        super(inv, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (OpenBeesAPI.getAPI().getCommonAPI().items.drone.getItem() == stack.getItem()) {
            return true;
        }
        if (stack.getItem().getClass().isAnnotationPresent(CombItem.class)) {
            return true;
        }
        return false;
    }
}
