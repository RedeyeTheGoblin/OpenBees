package info.inpureprojects.OpenBees.Common.Blocks.Tiles;

import cofh.lib.util.helpers.ServerHelper;
import cofh.lib.util.position.BlockPosition;
import info.inpureprojects.OpenBees.API.Common.Bees.CombItem;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.BeeUtils;
import info.inpureprojects.OpenBees.API.Common.Bees.IBee;
import info.inpureprojects.OpenBees.API.Common.Bees.IBeeKeepingTile;
import info.inpureprojects.OpenBees.API.Common.Tools.IFrameItem;
import info.inpureprojects.OpenBees.API.Common.Tools.ModifierCompute;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.Common.Managers.InventoryManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by den on 8/7/2014.
 */
public class TileApiary extends TileBase implements IBeeKeepingTile {

    public static final int code_allGood = 0;
    public static final int code_missingBee = 1;
    public static final int code_inventoryFull = 2;
    public static final int code_Night = 3;
    public static final int code_wrongBiome = 4;
    public static final int code_rain = 5;
    public static final int code_sky = 6;
    public static final int code_flower = 7;
    //----------------------------------------------
    // Why 45? There are 45 pixels in the life display bar.
    public static final int baseLifeTicks = 45;
    private static int breedingDelay = 20 * 7;
    private static int queenSlot = 3;
    private static int droneSlot = 8;
    private static int[] frameSlots = new int[]{0, 4, 11};
    private static int[] outputSlots = new int[]{1, 2, 5, 6, 7, 9, 10};
    private static int delay = 20 * 4;
    private int statusCode = 0;
    private int breedingProgress = 0;
    private int lifeProgress = 0;
    private int count = 0;
    private InventoryManager output;
    private boolean recheckFlowers = true;
    private ArrayList<IFrameItem> modifiers = new ArrayList();
    private ModifierCompute mods = new ModifierCompute(modifiers);

    public TileApiary() {
        super(12);
        output = new InventoryManager(outputSlots, this);
    }

    @Override
    public int[] getOutputSlotNumbers() {
        return outputSlots;
    }

    @Override
    public int[] getInputSlotForTop() {
        return new int[]{queenSlot, droneSlot, frameSlots[0], frameSlots[1], frameSlots[2]};
    }

    @Override
    public void onNeighborsChanged() {
        recheckFlowers = true;
        modifiers.clear();
        modifiers.trimToSize();
        modifiers.addAll(this.getFrames());
        modifiers.addAll(OpenBeesAPI.getAPI().getCommonAPI().beeManager.getModifierBlocksNear(this));
        mods = new ModifierCompute(modifiers);
    }

    @Override
    public IBee getDrone() {
        return OpenBeesAPI.getAPI().getCommonAPI().beeManager.convertStackToBee(this.getStackInSlot(droneSlot));
    }

    @Override
    public List<BlockPosition> getSurroundingBlocks() {
        return this.getPosition().getAdjacent(true);
    }

    @Override
    public World getWorld() {
        return this.getWorldObj();
    }

    @Override
    public BlockPosition getPosition() {
        return new BlockPosition(this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public List<IFrameItem> getFrames() {
        ArrayList<IFrameItem> frames = new ArrayList();
        for (int i = 0; i < this.size; i++) {
            if (i == 0 || i == 4 || i == 11) {
                if (this.getStackInSlot(i) != null) {
                    frames.add((IFrameItem) this.getStackInSlot(i).getItem());
                }
            }
        }
        return frames;
    }

    @Override
    public IBee getQueen() {
        return OpenBeesAPI.getAPI().getCommonAPI().beeManager.convertStackToBee(this.getStackInSlot(queenSlot));
    }

    public void onRemoval() {
        for (ItemStack i : stacks) {
            if (i != null) {
                this.getWorldObj().spawnEntityInWorld(new EntityItem(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord, i));
            }
        }
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        if (this.getWorld() != null) {
            this.getWorld().markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
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
    public void init() {
        this.onNeighborsChanged();
    }

    @Override
    public void onSlotChanged(int slot) {
        if (Arrays.asList(frameSlots).contains(slot)){
            this.onNeighborsChanged();
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        // Setting up the status flag for the GUI.
        boolean hasQueen = false;
        boolean hasPrincess = false;
        boolean hasDrone = false;
        if (this.getStackInSlot(queenSlot) == null) {
            this.setStatusCode(code_missingBee);
        } else {
            if (BeeUtils.instance.isPrincess(this.getStackInSlot(queenSlot))) {
                hasPrincess = true;
            } else if (BeeUtils.instance.isQueen(this.getStackInSlot(queenSlot))) {
                hasQueen = true;
            }
        }
        if (BeeUtils.instance.isDrone(this.getStackInSlot(droneSlot))) {
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
                this.setInventorySlotContents(queenSlot, OpenBeesAPI.getAPI().getCommonAPI().beeManager.getCurrentLogic().combine(this));
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
            IBee queen = OpenBeesAPI.getAPI().getCommonAPI().beeManager.convertStackToBee(this.getStackInSlot(queenSlot));
            // External modifiers

            // Night time
            if (!this.getWorldObj().isDaytime()) {
                if (!queen.getDominantGenome().getNocturnal().isBool() && !mods.canBypassNocturnal()) {
                    this.setStatusCode(code_Night);
                    return;
                }
            }
            // Climate
            if (!queen.getDominantGenome().getClimate().getRequiredClimate().isBiomeCompatible(this.getWorldObj().getBiomeGenForCoords(this.xCoord, this.zCoord)) && !mods.canBypassBiomeRequirement()) {
                this.setStatusCode(code_wrongBiome);
                return;
            }
            // Rain
            if (this.getWorldObj().isRaining() && !mods.canBypassRain()) {
                if (!queen.getDominantGenome().getRain().isBool()) {
                    this.setStatusCode(code_rain);
                    return;
                }
            }
            // Sky
            if (!this.getWorldObj().canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord) && !mods.canBypassOutdoorRequirement()) {
                if (!queen.getDominantGenome().getCave().isBool()) {
                    this.setStatusCode(code_sky);
                    return;
                }
            }
            // Flowers
            if (this.recheckFlowers) {
                if (!hasFlowers(queen, this.getSurroundingBlocks()) && !mods.canBypassFlowerRequirement()) {
                    this.setStatusCode(code_flower);
                    return;
                }
                this.recheckFlowers = false;
            }
            // Process this life tick.
            int ticks = queen.getLifeTicks();
            ticks--;
            this.getStackInSlot(queenSlot).getTagCompound().setInteger("life", ticks);
            this.setLifeProgress(ticks / queen.getDominantGenome().getLifespan().getNumber());
            // Produce combs.
            for (ItemStack i : OpenBeesAPI.getAPI().getCommonAPI().beeManager.getCurrentLogic().produceItemsOnTick(this)) {
                this.throwStacks(output.addStack(i));
            }
            // Long live the Queen... oh wait.
            if (ticks <= 0) {
                int drones = (3 * queen.getDominantGenome().getFertility().getNumber()) + mods.getFertilityModifier();
                for (int i = 0; i < drones; i++) {
                    throwStacks(output.addStack(OpenBeesAPI.getAPI().getCommonAPI().beeManager.getCurrentLogic().produceOffspring(this, modifiers, false)));
                }
                throwStacks(output.addStack(OpenBeesAPI.getAPI().getCommonAPI().beeManager.getCurrentLogic().produceOffspring(this, modifiers, true)));
                this.setInventorySlotContents(queenSlot, null);
                this.lifeProgress = 0;
            } else {
                queen.setLifeTicks(ticks);
                this.getStackInSlot(queenSlot).setTagCompound(queen.getNBT());
            }
            count = 0;
        }
    }

    public boolean hasFlowers(IBee queen, List<BlockPosition> list) {
        for (BlockPosition p : list) {
            if (p.blockExists(this.worldObj)) {
                if (queen.getDominantGenome().getFlower().isValid(this.worldObj, p.x, p.y, p.z, queen)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.setLifeProgress(tag.getInteger("lifeProgress"));
        this.setBreedingProgress(tag.getInteger("breedingProgress"));
        this.setStatusCode(tag.getInteger("status"));
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("breedingProgress", this.getBreedingProgress());
        tag.setInteger("lifeProgress", this.getLifeProgress());
        tag.setInteger("status", this.getStatusCode());
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        if (side == ForgeDirection.UP.ordinal()){
            if (this.isItemValidForSlot(slot, stack)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        if (side != ForgeDirection.UP.ordinal()){
            if (this.getStackInSlot(slot) != null){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack stack) {
        if (i == outputSlots[0] || i == outputSlots[1] || i == outputSlots[2] || i == outputSlots[3] || i == outputSlots[4] || i == outputSlots[5] || i == outputSlots[6]) {
            if (stack.getItem().getClass().isAnnotationPresent(CombItem.class)) {
                return true;
            } else {
                if (BeeUtils.instance.isDrone(stack) || BeeUtils.instance.isPrincess(stack)) {
                    return true;
                }
            }
        }
        if (i == queenSlot) {
            if (BeeUtils.instance.isQueen(stack) || BeeUtils.instance.isPrincess(stack)) {
                return true;
            }
        }
        if (Arrays.asList(frameSlots).contains(i)) {
            if (stack.getItem() instanceof IFrameItem) {
                return true;
            }
        }
        if (i == droneSlot) {
            if (BeeUtils.instance.isDrone(stack)) {
                return true;
            }
        }
        return false;
    }
}
