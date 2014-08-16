package info.inpureprojects.OpenBees.Common.Managers;

import info.inpureprojects.OpenBees.Common.NeedsMovedToCore;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/12/2014.
 */
@NeedsMovedToCore
public class InventoryManager {

    private int[] slots;
    private IInventory inventory;

    public InventoryManager(int[] slots, IInventory inventory) {
        this.slots = slots;
        this.inventory = inventory;
    }

    public ItemStack addStack(ItemStack stack) {
        for (int i : slots) {
            if (inventory.isItemValidForSlot(i, stack)) {
                if (inventory.getStackInSlot(i) != null) {
                    ItemStack current = inventory.getStackInSlot(i);
                    if (current.isItemEqual(stack)) {
                        if (ItemStack.areItemStackTagsEqual(current, stack)) {
                            if ((current.stackSize + stack.stackSize) <= current.getMaxStackSize()) {
                                current.stackSize += stack.stackSize;
                                return null;
                            } else {
                                int amt = current.getMaxStackSize() - current.stackSize;
                                stack.stackSize -= amt;
                                current.stackSize += amt;
                                return stack;
                            }
                        }
                    }
                } else {
                    inventory.setInventorySlotContents(i, stack);
                    return null;
                }
            }
        }
        return stack;
    }
}
