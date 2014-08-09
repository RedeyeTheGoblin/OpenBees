package info.inpureprojects.OpenBees.Client.Gui;

import info.inpureprojects.OpenBees.Client.Gui.Slots.SlotDrone;
import info.inpureprojects.OpenBees.Client.Gui.Slots.SlotDroneOrComb;
import info.inpureprojects.OpenBees.Client.Gui.Slots.SlotFrame;
import info.inpureprojects.OpenBees.Client.Gui.Slots.SlotPrincessOrQueen;
import info.inpureprojects.OpenBees.Client.ImageScanner;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.TileApiary;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/7/2014.
 */
public class ContainerApiary extends Container {

    public static final int adjustmentX = 0;
    public static final int adjustmentY = -6;
    private TileApiary tile;

    public ContainerApiary(EntityPlayer player, TileApiary tile) {
        this.tile = tile;
        this.bindPlayerInventory(player.inventory);
        ImageScanner i = new ImageScanner();
        i.load("assets/openbees/textures/gui/gui_apiary_map.png");
        int index = 0;
        for (ImageScanner.PixelData d : i.findTargets()) {
            if (index == 3) {
                this.addSlotToContainer(new SlotPrincessOrQueen(tile, index++, d.getX(), d.getY()));
            } else if (index == 8) {
                this.addSlotToContainer(new SlotDrone(tile, index++, d.getX(), d.getY()));
            }else if (index == 1 || index == 2 || index == 5 || index == 6 || index == 7 || index == 9 || index == 10) {
                this.addSlotToContainer(new SlotDroneOrComb(tile, index++, d.getX(), d.getY()));
            }else if (index == 0 || index == 4 || index == 11){
                this.addSlotToContainer(new SlotFrame(tile, index++, d.getX(), d.getY()));
            } else {
                this.addSlotToContainer(new Slot(tile, index++, d.getX(), d.getY()));
            }
        }
    }

    public TileApiary getTile() {
        return tile;
    }

    @Override
    public void addCraftingToCrafters(ICrafting player) {
        super.addCraftingToCrafters(player);
        player.sendProgressBarUpdate(this, 0, tile.getStatusCode());
    }

    @Override
    public void updateProgressBar(int c, int d) {
        super.updateProgressBar(c, d);
        switch(c){
            case 0:
                tile.setStatusCode(d);
                break;
        }
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
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            //merges the item into player inventory since its in the tileEntity
            if (slot < 9) {
                if (!this.mergeItemStack(stackInSlot, 0, 35, true)) {
                    return null;
                }
            }
            //places it into the tileEntity is possible since its in the player inventory
            else if (!this.mergeItemStack(stackInSlot, 0, 9, false)) {
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
}
