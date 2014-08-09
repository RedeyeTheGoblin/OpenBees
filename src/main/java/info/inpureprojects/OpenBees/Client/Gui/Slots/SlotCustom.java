package info.inpureprojects.OpenBees.Client.Gui.Slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/8/2014.
 */
public abstract class SlotCustom extends Slot{

    public SlotCustom(IInventory inv, int index, int x, int y) {
        super(inv, index, x, y);
    }

    @Override
    public abstract boolean isItemValid(ItemStack stack);
}
