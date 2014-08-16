package info.inpureprojects.OpenBees.Client.Gui.Slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/13/2014.
 */
public class SlotStorage extends SlotCustom {

    public SlotStorage(IInventory inv, int index, int x, int y) {
        super(inv, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return true;
    }
}
