package info.inpureprojects.OpenBees.Common.Blocks.Tiles;

import cofh.lib.util.helpers.ServerHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by den on 8/12/2014.
 */
public abstract class TileBase extends TileEntity implements ISidedInventory {

    public ItemStack[] stacks;
    public int size;
    private boolean init = false;

    public abstract void init();

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (ServerHelper.isClientWorld(this.worldObj)) {
            return;
        }
        if (!init){
            this.init();
            init = true;
        }
    }

    protected TileBase(int size) {
        this.size = size;
        this.stacks = new ItemStack[this.size];
    }

    public abstract int[] getOutputSlotNumbers();

    public abstract int[] getInputSlotForTop();

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        switch(side){
            case 1:
                return this.getInputSlotForTop();

        }
        return this.getOutputSlotNumbers();
    }

    @Override
    public abstract boolean canInsertItem(int slot, ItemStack stack, int side);

    @Override
    public abstract boolean canExtractItem(int slot, ItemStack stack, int side);

    @Override
    public int getSizeInventory() {
        return this.size;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return stacks[i];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amt) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        stacks[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
        this.onSlotChanged(slot);
    }

    public abstract void onSlotChanged(int slot);

    @Override
    public String getInventoryName() {
        return this.getClass().getName();
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public abstract boolean isItemValidForSlot(int i, ItemStack stack);

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        for (int i = 0; i < stacks.length; i++) {
            String key = "inventory" + String.valueOf(i);
            if (tag.hasKey(key)) {
                NBTTagCompound temp = tag.getCompoundTag(key);
                stacks[i] = ItemStack.loadItemStackFromNBT(temp);
            } else {
                stacks[i] = null;
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        for (int i = 0; i < stacks.length; i++) {
            String key = "inventory" + String.valueOf(i);
            if (stacks[i] != null) {
                tag.setTag(key, stacks[i].writeToNBT(new NBTTagCompound()));
            }
        }
    }

    protected void throwStacks(ItemStack stack) {
        if (stack != null) {
            this.getWorldObj().spawnEntityInWorld(new EntityItem(this.getWorldObj(), this.xCoord, this.yCoord + 1, this.zCoord, stack));
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        S35PacketUpdateTileEntity packet = new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
        return packet;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.func_148857_g());
    }
}
