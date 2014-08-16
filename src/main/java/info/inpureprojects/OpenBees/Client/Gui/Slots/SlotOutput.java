package info.inpureprojects.OpenBees.Client.Gui.Slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/8/2014.
 */
public class SlotOutput extends SlotCustom {
    public SlotOutput(IInventory inv, int index, int x, int y) {
        super(inv, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }
}
