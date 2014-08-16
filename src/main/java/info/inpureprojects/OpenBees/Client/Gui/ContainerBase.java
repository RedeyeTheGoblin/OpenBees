package info.inpureprojects.OpenBees.Client.Gui;

import info.inpureprojects.OpenBees.Common.NeedsMovedToCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/13/2014.
 */
@NeedsMovedToCore
public abstract class ContainerBase extends Container {

    public int adjustmentX = 0;
    public int adjustmentY = 0;
    public int hotBarAdjustmentX = 0;
    public int hotBarAdjustmentY = 0;
    public int invSize = 0;

    protected ContainerBase(EntityPlayer player, int adjustmentX, int adjustmentY, int hotBarAdjustmentX, int hotBarAdjustmentY, int invSize) {
        this.adjustmentX = adjustmentX;
        this.adjustmentY = adjustmentY;
        this.hotBarAdjustmentX = hotBarAdjustmentX;
        this.hotBarAdjustmentY = hotBarAdjustmentY;
        this.invSize = invSize;
        this.bindPlayerInventory(player.inventory);
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                        8 + j * 18 + adjustmentX, 90 + i * 18 + adjustmentY));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18 + hotBarAdjustmentX, 142 + hotBarAdjustmentY));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting player) {
        super.addCraftingToCrafters(player);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }

    @Override
    public void updateProgressBar(int c, int d) {
        super.updateProgressBar(c, d);
    }

    // This method implementation copied from MFR.
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);
        int machInvSize = invSize;

        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            if (slot < machInvSize) {
                if (!mergeItemStack(stackInSlot, machInvSize, inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!mergeItemStack(stackInSlot, 0, machInvSize, false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }

            if (stackInSlot.stackSize == stack.stackSize) {
                return null;
            }

            slotObject.onPickupFromSlot(player, stackInSlot);
        }

        return stack;
    }

    // This method implementation copied from MFR.
    @Override
    protected boolean mergeItemStack(ItemStack stack, int slotStart, int slotRange, boolean reverse) {
        boolean successful = false;
        int slotIndex = !reverse ? slotStart : slotRange - 1;
        int iterOrder = !reverse ? 1 : -1;

        Slot slot;
        ItemStack existingStack;

        if (stack.isStackable()) {
            while (stack.stackSize > 0 && (!reverse && slotIndex < slotRange || reverse && slotIndex >= slotStart)) {
                slot = (Slot) this.inventorySlots.get(slotIndex);
                existingStack = slot.getStack();

                if (slot.isItemValid(stack) && existingStack != null &&
                        existingStack.getItem().equals(stack.getItem()) &&
                        (!stack.getHasSubtypes() ||
                                stack.getItemDamage() == existingStack.getItemDamage()) &&
                        ItemStack.areItemStackTagsEqual(stack, existingStack)) {
                    int existingSize = existingStack.stackSize + stack.stackSize;
                    int maxStack = Math.min(stack.getMaxStackSize(), slot.getSlotStackLimit());

                    if (existingSize <= maxStack) {
                        stack.stackSize = 0;
                        existingStack.stackSize = existingSize;
                        slot.onSlotChanged();
                        successful = true;
                    } else if (existingStack.stackSize < maxStack) {
                        stack.stackSize -= maxStack - existingStack.stackSize;
                        existingStack.stackSize = maxStack;
                        slot.onSlotChanged();
                        successful = true;
                    }
                }

                slotIndex += iterOrder;
            }
        }

        if (stack.stackSize > 0) {
            slotIndex = !reverse ? slotStart : slotRange - 1;

            while (stack.stackSize > 0 && (!reverse && slotIndex < slotRange || reverse && slotIndex >= slotStart)) {
                slot = (Slot) this.inventorySlots.get(slotIndex);
                existingStack = slot.getStack();

                if (slot.isItemValid(stack) && existingStack == null) {
                    int maxStack = Math.min(stack.getMaxStackSize(), slot.getSlotStackLimit());
                    existingStack = stack.splitStack(Math.min(stack.stackSize, maxStack));
                    slot.putStack(existingStack);
                    slot.onSlotChanged();
                    successful = true;
                }

                slotIndex += iterOrder;
            }
        }

        return successful;
    }
}
