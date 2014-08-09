package info.inpureprojects.OpenBees.Client.Gui.Slots;

import info.inpureprojects.OpenBees.API.Common.Tools.IFrameItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/8/2014.
 */
public class SlotFrame extends SlotCustom {

    public SlotFrame(IInventory inv, int index, int x, int y) {
        super(inv, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (stack.getItem() instanceof IFrameItem) {
            return true;
        }
        return false;
    }
}
