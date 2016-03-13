package OpenBees.block.tileEntities;

import OpenBees.OpenBees;
import OpenBees.genetics.IBee;
import OpenBees.genetics.IBeeKeepingTile;
import OpenBees.item.interfaces.IFrameItem;
import OpenBees.item.interfaces.combItem;
import OpenBees.utility.modifierHelper;
import cofh.lib.util.helpers.ServerHelper;
import cofh.lib.util.position.BlockPosition;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tileApiary extends blockBaseTile implements IBeeKeepingTile {

    public static final int code_allGood = 0;
    public static final int code_missingBee = 1;
    public static final int code_inventoryFull = 2;
    public static final int code_Night = 3;
    public static final int code_wrongBiome = 4;
    public static final int code_Rain = 5;
    public static final int code_NoSky = 6;
    public static final int code_NoFlower = 7;

    /** Den says the life display bar is 45 pixels
     *  and thats why this varible is 45...
     */
    public static final int baseLifeTicks = 45;
    private static int breedingDelay = 20 * 7;
    private static int queenSlot = 3;
    private static int droneSlot = 8;
    private static int[] frameSlots = new int[]{0, 4, 11};
    private static int[] outputSlots = new int[]{1, 2, 5, 6, 7, 9 , 10};
    private static int delay = 20 * 4;
    private int statusCode = 0;
    private int breedingProgress = 0;
    private int lifeProgress = 0;
    private int count = 0;

    private boolean recheckFlowers = true;
    private ArrayList<IFrameItem> modifiers = new ArrayList();
    private modifierHelper mods = new modifierHelper(modifiers);

    public tileApiary()
    {
        super(12);
    }

    @Override
    public int[] getOutputSlotNumbers()
    {
        return outputSlots;
    }

    @Override
    public int[] getInputSlotForTop() {
        return new int[]{queenSlot, droneSlot, frameSlots[0], frameSlots[1], frameSlots[2]};
    }

    @Override
    public void onNeighboursChanged() {
        recheckFlowers = true;
        modifiers.clear();
        modifiers.trimToSize();
        modifiers.addAll(this.getFrames());
        modifiers.addAll(OpenBees.coreBeeHandler.getModifierBlocksNear(this));
        mods = new modifierHelper(modifiers);
    }

    @Override
    public IBee getDrone() {
        return OpenBees.coreBeeHandler.convertStacktoBee(this.getStackInSlot(droneSlot));
    }

    @Override
    public List<BlockPosition> getSurroundingBlocks() {
        return this.getPosition().getAdjacent(true);
    }

    @Override
    public List<BlockPosition> getSurroundingBlocks(int distance) {
        ArrayList closeBlocks = new ArrayList();

        for (int yCount = this.yCoord; yCount <= this.yCoord + distance; yCount++ ) {
            for (int xCount = this.xCoord - distance; xCount <= this.xCoord + distance; xCount++) {
                for (int zCount = this.zCoord - distance; zCount <= this.zCoord + distance; zCount++) {
                    closeBlocks.add(new BlockPosition(xCount, yCount, zCount));
                }
            }
        }
        return closeBlocks;
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
        for (int i = 1; i < this.size; i++) {
            //If on a frame slot
            if (i == 0 || i == 4 || i ==11) {
                if (this.getStackInSlot(i) != null) {
                    frames.add((IFrameItem) this.getStackInSlot(i).getItem());
                }
            }
        }
        return frames;
    }

    @Override
    public IBee getQueen() {
        return OpenBees.coreBeeHandler.convertStacktoBee(this.getStackInSlot(queenSlot));
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
        return this.breedingProgress;
    }

    public void setBreedingProgress(int breedingProgress) {
        this.breedingProgress = breedingProgress;
    }

    @Override
    public void init() {
        this.onNeighboursChanged();
    }

    @Override
    public void onSlotChanged(int slot) {
        if (Arrays.asList(frameSlots).contains(slot)) {
            this.onNeighboursChanged();
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (ServerHelper.isClientWorld(this.worldObj)) {
            return;
        }

        //Check for abnormal statuses
        boolean hasQueen = false;
        boolean hasPrincess = false;
        boolean hasDrone = false;

        //Check if there is a queen in the slot, or a princess to be combined
        if (this.getStackInSlot(queenSlot) == null) {
            this.setStatusCode(code_missingBee);
        } else {
            if (OpenBees.coreBeeHelper.isPrincess(this.getStackInSlot(queenSlot))) {
                hasPrincess = true;
            } else if (OpenBees.coreBeeHelper.isQueen(this.getStackInSlot(queenSlot))) {
                hasQueen = true;
            }
        }

        if (OpenBees.coreBeeHelper.isDrone(this.getStackInSlot(droneSlot))) {
            hasDrone = true;
        }

        //Checking for empty output slots
        boolean emptySlot = false;
        for (int slot : outputSlots) {
            if (this.getStackInSlot(slot) == null) {
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

        //If no queen, combine princess and drone to make one... hopefully.
        if (!hasQueen) {
            this.lifeProgress = 0;
        }
        if (hasPrincess && hasDrone) {
            breedingProgress++;
            if (breedingProgress > breedingDelay) {
                this.setInventorySlotContents(queenSlot, OpenBees.coreBeeHandler.getCurrentLogic().combine(this));
                this.decrStackSize(droneSlot, 1);
                breedingProgress = 0;
                return;
            }
        }

        //Bee ticks are slower than server ticks, so add delay
        count++;
        if (count >= delay) {
            if(!hasQueen) {
                return;
            }

            IBee queen = OpenBees.coreBeeHandler.convertStacktoBee(this.getStackInSlot(queenSlot));

            //Check external conditions

            //Night
            if (!this.getWorldObj().isDaytime()) {
                if (!queen.getDominantGenome().getNocturnal().isBool() && !mods.canBypassNocturnal()) {
                    this.setStatusCode(code_Night);
                    return;
                }
            }

            //Climate
            if (!queen.getDominantGenome().getClimate().getRequiredClimate().isBiomeCompatible(this.getWorldObj().getBiomeGenForCoords(this.xCoord, this.yCoord)) && !mods.canBypassBiome()) {
                this.setStatusCode(code_wrongBiome);
                return;
            }

            //Rain
            if (this.getWorldObj().isRaining() & !mods.canBypassRain()) {
                if (!queen.getDominantGenome().getRain().isBool()) {
                    this.setStatusCode(code_Rain);
                    return;
                }
            }

            //Sky
            if (!this.getWorldObj().canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord) && !mods.canBypassCave()) {
                if (!queen.getDominantGenome().getCave().isBool()) {
                    this.setStatusCode(code_NoSky);
                    return;
                }
            }

            //Flowers
            if (this.recheckFlowers) {
                if (this.getStackInSlot(queenSlot) != null) {
                    if (!hasFlowers(queen, this.getSurroundingBlocks(this.getQueen().getDominantGenome().getTerritory().getNumber())) && !mods.canBypassFlowers()) {
                        this.setStatusCode(code_NoFlower);
                        return;
                    }
                } else {
                    if (!hasFlowers(queen, this.getSurroundingBlocks()) && !mods.canBypassFlowers()) {
                        this.setStatusCode(code_NoFlower);
                        return;
                    }
                }
                this.recheckFlowers = false;
            }

            //Process the life tick
            int ticks = queen.getLifeTicks();
            ticks--;
            //this.getStackInSlot(queenSlot).getTagCompound().setInteger("life", ticks);
            this.setLifeProgress(ticks / queen.getDominantGenome().getLifespan().getNumber());

            //Produce combs etc
            for (ItemStack stack : OpenBees.coreBeeHandler.getCurrentLogic().produceItemsOnTick(this)) {
                this.throwStacks(output.addStack(stack));
            }

            //Check if queen is still alive...
            if (ticks <= 0) {
                int drones = (3 * Math.round(queen.getDominantGenome().getFertility().getNumber() * mods.getFertilityModifier()));
                if (drones == 0) drones++;
                for (int i = 0; i < drones; i++) {
                    throwStacks(output.addStack(OpenBees.coreBeeHandler.getCurrentLogic().produceOffspring(this, modifiers, false)));
                }
                throwStacks(output.addStack(OpenBees.coreBeeHandler.getCurrentLogic().produceOffspring(this, modifiers, true)));
                this.setInventorySlotContents(queenSlot, null);
                this.lifeProgress = 0;
            } else {
                queen.setLifeTicks(ticks);
                this.getStackInSlot(queenSlot).setTagCompound(queen.getNBT());
                this.getStackInSlot(queenSlot).getTagCompound().setInteger("life", ticks);
            }
            count = 0;
        }
    }

    public boolean hasFlowers(IBee queen, List<BlockPosition> blocks) {
        for (BlockPosition pos : blocks) {
            if (pos.blockExists(this.worldObj)) {
                if (queen.getDominantGenome().getFlower().isValid(this.worldObj, pos.x, pos.y, pos.z, queen)) {
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
        this.setStatusCode(tag.getInteger("statusCode"));
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("lifeProgress", this.getLifeProgress());
        tag.setInteger("breedingProgress", this.getBreedingProgress());
        tag.setInteger("statusCode", this.getStatusCode());
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (slot == outputSlots[0] || slot == outputSlots[1] || slot == outputSlots[2] || slot == outputSlots[3] || slot == outputSlots[4] || slot == outputSlots[5] || slot == outputSlots[6]) {
            if (stack.getItem().getClass().isAnnotationPresent(combItem.class)) {
                return true;
            } else {
                if (OpenBees.coreBeeHelper.isDrone(stack) || OpenBees.coreBeeHelper.isPrincess(stack)) {
                    return true;
                }
            }
        }

        if (slot == queenSlot) {
            if (OpenBees.coreBeeHelper.isQueen(stack) || OpenBees.coreBeeHelper.isPrincess(stack)) {
                return true;
            }
        }

        if (Arrays.asList(frameSlots).contains(slot)) {
            if (stack.getItem() instanceof IFrameItem) {
                return true;
            }
        }

        if (slot == droneSlot) {
            if (OpenBees.coreBeeHelper.isDrone(stack)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int getSizeInventory() {
        return this.size;
    }
}
