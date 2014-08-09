package info.inpureprojects.OpenBees.Common.Blocks.Tiles;

import cofh.lib.inventory.IInventoryManager;
import cofh.lib.inventory.InventoryManager;
import cofh.lib.util.helpers.ServerHelper;
import info.inpureprojects.OpenBees.API.Common.Bees.CombItem;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.BeeProduct;
import info.inpureprojects.OpenBees.API.Common.Bees.IBee;
import info.inpureprojects.OpenBees.API.Common.Tools.IFrameItem;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.Common.ModuleOpenBees;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by den on 8/7/2014.
 */
public class TileApiary extends TileEntity implements IInventory {

    public static final int code_allGood = 0;
    public static final int code_missingBee = 1;
    public static final int code_inventoryFull = 2;
    // Why 45? There are 45 pixels in the life display bar.
    public static final int baseLifeTicks = 45;
    private static int breedingDelay = 20 * 7;
    private static int queenSlot = 3;
    private static int droneSlot = 8;
    private static int[] frameSlots = new int[]{0, 4, 11};
    private static int[] outputSlots = new int[]{1, 2, 5, 6, 7, 9, 10};
    private static int delay = 20 * 4;
    public int size = 12;
    public ItemStack[] stacks;
    private int statusCode = 0;
    private int breedingProgress = 0;
    private int lifeProgress = 0;
    private int count = 0;
    private IInventoryManager manager;

    public TileApiary() {
        this.stacks = new ItemStack[size];
        manager = InventoryManager.create(this, ForgeDirection.UNKNOWN);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getLifeProgress() {
        return lifeProgress;
    }

    public void setLifeProgress(int lifeProgress) {
        this.lifeProgress = lifeProgress;
    }

    public int getBreedingProgress() {
        return breedingProgress;
    }

    public void setBreedingProgress(int breedingProgress) {
        this.breedingProgress = breedingProgress;
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

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (ServerHelper.isClientWorld(this.worldObj)){
            return;
        }
        // Setting up the status flag for the GUI.
        boolean hasQueen = false;
        boolean hasPrincess = false;
        boolean hasDrone = false;
        if (this.getStackInSlot(queenSlot) == null) {
            this.setStatusCode(code_missingBee);
        } else {
            if (this.getStackInSlot(queenSlot).getItem() == OpenBeesAPI.getAPI().getCommonAPI().items.princess.getItem()) {
                hasPrincess = true;
            } else {
                hasQueen = true;
            }
        }
        if (this.getStackInSlot(droneSlot) != null) {
            hasDrone = true;
        }
        boolean emptySlot = false;
        for (int i : outputSlots) {
            if (this.getStackInSlot(i) == null) {
                emptySlot = true;
                break;
            }
        }
        if (!emptySlot) {
            this.setStatusCode(code_inventoryFull);
            return;
        }
        if (hasQueen && emptySlot) {
            this.setStatusCode(code_allGood);
        }
        if (!hasQueen) {
            this.lifeProgress = 0;
        }
        // Attempt to make a queen.
        if (hasPrincess && hasDrone) {
            breedingProgress++;
            if (breedingProgress >= breedingDelay) {
                this.setInventorySlotContents(queenSlot, OpenBeesAPI.getAPI().getCommonAPI().beeManager.getCurrentLogic().combine(this.getStackInSlot(queenSlot), this.getStackInSlot(droneSlot)));
                this.decrStackSize(droneSlot, 1);
                breedingProgress = 0;
                return;
            }
        }
        // The delay. Bee ticks are slower than the normal server tick.
        count++;
        if (count >= delay) {
            if (!hasQueen) {
                return;
            }
            // Process this life tick.
            IBee queen = OpenBeesAPI.getAPI().getCommonAPI().beeManager.convertStackToBee(this.getStackInSlot(queenSlot));
            int ticks = queen.getLifeTicks();
            ticks--;
            this.getStackInSlot(queenSlot).getTagCompound().setInteger("life", ticks);
            this.setLifeProgress(ticks / queen.getDominantGenome().getLifespan().getNumber());
            // Produce combs.
            Random r = new Random();
            for (BeeProduct p : queen.getDominantGenome().getSpecies().getPotentialProducts()){
                int odds = r.nextInt(p.getChance());
                if (odds <= 100){
                    ItemStack comb = p.getStack().copy();
                    ItemStack s = manager.addItem(comb);
                    if (s != null){
                        this.throwStacks(s);
                    }
                }
            }
            // Long live the Queen... oh wait.
            if (ticks <= 0) {
                int drones = 3 * queen.getDominantGenome().getFertility().getNumber();
                for (int i = 0; i < drones; i++) {
                    ItemStack s = manager.addItem(OpenBeesAPI.getAPI().getCommonAPI().beeManager.getCurrentLogic().produceOffspring(this.getStackInSlot(queenSlot), false));
                    if (s != null){
                        this.throwStacks(s);
                    }
                }
                ItemStack s = manager.addItem(OpenBeesAPI.getAPI().getCommonAPI().beeManager.getCurrentLogic().produceOffspring(this.getStackInSlot(queenSlot), true));
                if (s != null){
                    this.throwStacks(s);
                }
                this.setInventorySlotContents(queenSlot, null);
            } else {
                queen.setLifeTicks(ticks);
                this.getStackInSlot(queenSlot).setTagCompound(queen.getNBT());
            }
            count = 0;
        }
    }

    private void throwStacks(ItemStack stack){
        this.getWorldObj().spawnEntityInWorld(new EntityItem(this.getWorldObj(), this.xCoord, this.yCoord + 1, this.zCoord, stack));
    }

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
        this.setLifeProgress(tag.getInteger("lifeProgress"));
        this.setBreedingProgress(tag.getInteger("breedingProgress"));
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
        tag.setInteger("breedingProgress", this.getBreedingProgress());
        tag.setInteger("lifeProgress", this.getLifeProgress());
    }

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
    }

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
    public boolean isItemValidForSlot(int i, ItemStack stack) {
        if (i == outputSlots[0] || i == outputSlots[1] || i == outputSlots[2] || i == outputSlots[3] || i == outputSlots[4] || i == outputSlots[5] || i == outputSlots[6]){
            if (stack.getItem().getClass().isAnnotationPresent(CombItem.class)){
                return true;
            }else{
                if (stack.getItem() == OpenBeesAPI.getAPI().getCommonAPI().items.drone.getItem() || stack.getItem() == OpenBeesAPI.getAPI().getCommonAPI().items.princess.getItem()){
                    return true;
                }
            }
        }
        if (i == queenSlot){
            if (stack.getItem() == OpenBeesAPI.getAPI().getCommonAPI().items.queen.getItem() || stack.getItem() == OpenBeesAPI.getAPI().getCommonAPI().items.princess.getItem()){
                return true;
            }
        }
        if (Arrays.asList(frameSlots).contains(i)){
            if (stack.getItem() instanceof IFrameItem){
                return true;
            }
        }
        if (i == droneSlot){
            if (stack.getItem() == OpenBeesAPI.getAPI().getCommonAPI().items.drone.getItem()){
                return true;
            }
        }
        return false;
    }
}
