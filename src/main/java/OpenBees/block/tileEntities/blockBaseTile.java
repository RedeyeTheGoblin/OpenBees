package OpenBees.block.tileEntities;

import OpenBees.block.tileEntities.IRemoveTile;
import OpenBees.utility.inventoryHelper;
import cofh.lib.util.helpers.ServerHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class blockBaseTile extends TileEntity implements ISidedInventory, IRemoveTile {

    public ItemStack[] stacks;
    public int size;
    protected inventoryHelper output;
    private boolean init = false;

    protected blockBaseTile(int size) {
        this.size = size;
        this.stacks = new ItemStack[this.size];
        output = new inventoryHelper(this.getOutputSlotNumbers(), this);
    }

    public void onRemoval() {
        for (ItemStack iStack : stacks) {
            if (iStack != null) {
                this.getWorldObj().spawnEntityInWorld(new EntityItem(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord,iStack));
            }
        }
        this.invalidate();
    }

    public abstract void init();

    @Override
    public void updateEntity() {
        super.updateEntity();
        //Needs to be copied into sub-classess
        if (ServerHelper.isClientWorld(this.worldObj)) {
            return;
        }
        //to here, btw.
        if (!init) {
            this.init();
            init = true;
        }
    }

    public abstract int[] getOutputSlotNumbers();

    public abstract int[] getInputSlotForTop();

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        switch (side) {
            case 1:
                return this.getInputSlotForTop();
        }
        return this.getOutputSlotNumbers();
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        if (side == ForgeDirection.UP.ordinal()) {
            if (this.isItemValidForSlot(slot, stack)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        if (side != ForgeDirection.UP.ordinal()) {
            if (this.getStackInSlot(slot) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getSizeInventory() {
        return this.size;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return stacks[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amount);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    public ItemStack decrStackSizeNoNotify(int slot, int amount) {
        ItemStack stack = stacks[slot];
        if (stack != null) {
            if (stack.stackSize <= amount) {
                stacks[slot] = null;
            } else {
                stack = stack.splitStack(amount);
                if (stack.stackSize == 0) {
                    stacks[slot] = null;
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
    public String getInventoryName()
    {
        return this.getClass().getName();
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
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
    public abstract boolean isItemValidForSlot(int slot, ItemStack stack);

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        for (int i=0; i < stacks.length; i++) {
            String key = "inventory" + String.valueOf(i);
            if (tag.hasKey(key)) {
                NBTTagCompound tempTag = tag.getCompoundTag(key);
                stacks[i] = ItemStack.loadItemStackFromNBT(tempTag);
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
    public void onDataPacket(NetworkManager netMan, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }
}
