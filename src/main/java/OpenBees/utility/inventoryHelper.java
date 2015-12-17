package OpenBees.utility;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class inventoryHelper {

    private int[] slots;
    private IInventory inventory;

    public inventoryHelper(int[] slots, IInventory inventory)
    {
        this.slots = slots;
        this.inventory = inventory;
    }

    public ItemStack addStack(ItemStack stack)
    {
        for (int slotNo : slots)
        {
            if (inventory.isItemValidForSlot(slotNo, stack))
            {
                if (inventory.getStackInSlot(slotNo) != null)
                {
                    ItemStack current = inventory.getStackInSlot(slotNo);
                    if (current.isItemEqual(stack))
                    {
                        if (ItemStack.areItemStackTagsEqual(current, stack))
                        {
                            if ((current.stackSize + stack.stackSize) <= current.getMaxStackSize())
                            {
                                current.stackSize += stack.stackSize;
                                return null;
                            } else
                            {
                                int amount = current.getMaxStackSize() - current.stackSize;
                                stack.stackSize -= amount;
                                current.stackSize += amount;
                                return stack;
                            }
                        }
                    }
                } else
                {
                    inventory.setInventorySlotContents(slotNo, stack);
                    return null;
                }
            }
        }
        return stack;
    }
}
