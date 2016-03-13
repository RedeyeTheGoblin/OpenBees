package OpenBees.block.tileEntities;


import OpenBees.OpenBees;
import OpenBees.client.gui.containerExtractor;
import OpenBees.utility.craftingHelpers.IExtractorRecipe;
import OpenBees.utility.craftingHelpers.combProduct;
import OpenBees.utility.extractorCraftingManager;
import OpenBees.utility.inventoryHelper;
import OpenBees.utility.logHelper;
import cofh.lib.util.helpers.ServerHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.*;

public class tileExtractor extends tileBasePowered {

    private static int maxRFt = 20;
    private int currentRF = 0;
    private static int[] outputSlots = new int[]{0,1,2,4,5,6,7,8,9};
    private static int inputSlot = 3;
    private IExtractorRecipe currentRecipe;
    private int recipeProgress;
    private int recipeTime;
    private boolean jammed = false;
    private boolean validRecipe = false;

    public tileExtractor()
    {
        super(30, 10000);
    }

    @Override
    public void init() {

    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        validRecipe = OpenBees.instance.extractorRecipes.validateRecipe(this);

        if (!validRecipe) {
            return;
        }

        if (ServerHelper.isClientWorld(this.worldObj)){
            return;
        }

        int validSlots = 0;
        for (int i : outputSlots) {
            for (combProduct prod : this.currentRecipe.getResult()) {
                if (this.getStackInSlot(i) == null) {
                    validSlots++;
                } else if (this.getStackInSlot(i).isItemEqual(prod.getStack()) && (this.getStackInSlot(i).stackSize + prod.getStack().stackSize < this.getStackInSlot(i).getMaxStackSize())) {
                    validSlots++;
                }
                if (validSlots == 0){
                    jammed = true;
                    return;
                }
            }
        }

        if (this.extractEnergy(ForgeDirection.UNKNOWN, 20, true) < maxRFt) {
            this.currentRF =0;
            return;
        } else {
            this.currentRF = 20;
        }

        this.recipeTime = this.currentRecipe.getRecipeDelayInSeconds() * 20;
        if (this.recipeProgress >= this.recipeTime) {
            this.decrStackSizeNoNotify(inputSlot, this.currentRecipe.getInput().stackSize);

            Random rand = new Random();
            for (combProduct prod : this.currentRecipe.getResult()) {
                float odds = rand.nextFloat();

                if (odds <= prod.getChance()) {
                    this.throwStacks(output.addStack(prod.getStack().copy()));
                }
            }
            this.setRecipeProgress(0);
        } else {
            this.extractEnergy(ForgeDirection.UNKNOWN, this.currentRF, false);
            this.recipeProgress++;
        }
    }

    @Override
    public void onRemoval() {
        if (stacks[inputSlot] != null) {
            this.getWorldObj().spawnEntityInWorld(new EntityItem(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord, stacks[inputSlot]));
        }

        for (int i : outputSlots) {
            if (stacks[i] != null) {
                this.getWorldObj().spawnEntityInWorld(new EntityItem(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord, stacks[i]));
            }
        }
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack stack) {
        boolean valid = false;
        if (i == inputSlot) {
            if (this.getStackInSlot(inputSlot).isItemEqual(stack)) {
               valid = true;
            }
        } else if (i != inputSlot) {
            valid = true;
        }

        return valid;
    }

    public void setCurrentRecipe(IExtractorRecipe recipe) {
        if (this.currentRecipe != null && recipe != null) {
            if (!this.currentRecipe.doesMatch(recipe.getInput())) {
                this.currentRecipe = recipe;
                this.recipeProgress = 0;
            }
        } else {
            this.currentRecipe = recipe;
            this.recipeProgress = 0;
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.setRecipeProgress(tag.getInteger("recipeProgress"));
        this.setRecipeTime(tag.getInteger("recipeTime"));
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("recipeProgress", this.getRecipeProgress());
        tag.setInteger("recipeTime", this.getRecipeTime());
    }

    @Override
    public int getInfoMaxEnergyPerTick() {
        return maxRFt;
    }

    public void setCurrentRF(int currentRF) {
        this.currentRF = currentRF;
    }

    @Override
    public int getInfoEnergyPerTick() {
        return currentRF;
    }

    public int getRecipeTime() {
        return recipeTime;
    }

    public void setRecipeTime(int recipeTime) {
        this.recipeTime = recipeTime;
    }

    public int getRecipeProgress() {
        return recipeProgress;
    }

    public void setRecipeProgress(int recipeProgress) {
        this.recipeProgress = recipeProgress;
    }

    @Override
    public int[] getOutputSlotNumbers() {
        return outputSlots;
    }

    @Override
    public int[] getInputSlotForTop() {
        return new int[]{inputSlot};
    }

    @Override
    public void onSlotChanged(int slot) {
    }
}
