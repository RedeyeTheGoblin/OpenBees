package info.inpureprojects.OpenBees.Common.Blocks.Tiles;

import cofh.lib.util.helpers.ServerHelper;
import info.inpureprojects.OpenBees.Client.Gui.ContainerCarpenter;
import info.inpureprojects.OpenBees.Common.Managers.CarpenterCraftingManager;
import info.inpureprojects.OpenBees.Common.Managers.ICarpenterRecipe;
import info.inpureprojects.OpenBees.Common.Managers.InventoryManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by den on 8/13/2014.
 */
public class TileCarpenter extends TileBasePoweredTank implements IGhostSlotTile, IPreviewSlotTile {

    private ICarpenterRecipe currentRecipe;
    private int recipeProgess = 0;
    private int recipeTime = 0;
    private InventoryManager manager;
    private boolean jammed = false;
    private static int maxRFt = 20;
    private int currentRF = 0;
    private int count = 0;
    private static int delay = 3 * 20;

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

    public TileCarpenter() {
        super(30, 10000, ContainerCarpenter.canSlot, 10000);
    }

    @Override
    public void init() {
        manager = new InventoryManager(new int[]{ContainerCarpenter.outputSlot}, this);
        CarpenterCraftingManager.getInstance().validateRecipe(this);
    }

    public void setCurrentRecipe(ICarpenterRecipe currentRecipe) {
        this.currentRecipe = currentRecipe;
        this.recipeProgess = 0;
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        int i = super.fill(from, resource, doFill);
        if (i > 0){
            CarpenterCraftingManager.getInstance().validateRecipe(this);
            this.getWorldObj().markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
        return i;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        FluidStack f = super.drain(from, resource, doDrain);
        if (f != null){
            CarpenterCraftingManager.getInstance().validateRecipe(this);
            this.getWorldObj().markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
        return f;
    }

    public int getRecipeTime() {
        return recipeTime;
    }

    public void setRecipeTime(int recipeTime) {
        this.recipeTime = recipeTime;
    }

    public int getRecipeProgess() {
        return recipeProgess;
    }

    public void setRecipeProgess(int recipeProgess) {
        this.recipeProgess = recipeProgess;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (ServerHelper.isClientWorld(this.worldObj)) {
            return;
        }
        if (count <= delay){
            count++;
            return;
        }
        count = 0;
        //System.out.println(this.getTank().getFluidAmount());
        if (this.currentRecipe != null) {
            int matched = 0;
            List<Integer> targets = new ArrayList();
            List<ItemStack> list = this.currentRecipe.getItemsToRemove(this);
            for (ItemStack i : list) {
                for (Integer n : ContainerCarpenter.inputSlots) {
                    if (i == null) {
                        matched++;
                        break;
                    }
                    if (this.getStackInSlot(n) != null) {
                        if (OreDictionary.itemMatches(i, this.getStackInSlot(n), false)) {
                            targets.add(n);
                            matched++;
                            break;
                        }
                    }
                }
            }
            if (this.currentRecipe.getFluidToRemove() == null) {
                matched++;
            }else{
                if (this.getTank().getFluid() != null) {
                    if (this.getTank().getFluid().containsFluid(this.currentRecipe.getFluidToRemove())) {
                        matched++;
                    }
                }
            }
            if (matched == 10) {
                // Recipe is good.
                // Are we jammed?
                if (this.getStackInSlot(ContainerCarpenter.outputSlot) != null) {
                    if (!this.getStackInSlot(ContainerCarpenter.outputSlot).isItemEqual(this.currentRecipe.getResult())) {
                        jammed = true;
                    }
                    if ((this.getStackInSlot(ContainerCarpenter.outputSlot).stackSize + this.currentRecipe.getResult().stackSize) > this.getStackInSlot(ContainerCarpenter.outputSlot).getMaxStackSize()) {
                        jammed = true;
                    }
                }else{
                    jammed = false;
                }
                if (jammed) {
                    return;
                }
                if (this.extractEnergy(ForgeDirection.UNKNOWN, 20, true) < maxRFt){
                    this.currentRF = 0;
                    return;
                }else{
                    this.currentRF = 20;
                }
                this.recipeTime = this.currentRecipe.getRecipeDelayInSeconds() * 20;
                if (this.recipeProgess >= this.recipeTime) {
                    if (this.currentRecipe.getFluidToRemove() != null) {
                        this.getTank().drain(this.currentRecipe.getFluidToRemove().amount, true);
                    }
                    for (Integer i : targets) {
                        this.decrStackSize(i, 1);
                    }
                    this.throwStacks(this.manager.addStack(this.currentRecipe.getResultCopy()));
                    this.recipeProgess = 0;
                } else {
                    this.extractEnergy(ForgeDirection.UNKNOWN, this.currentRF, false);
                    this.recipeProgess++;
                }
            }else{
                this.currentRF = 0;
            }
        }
    }

    @Override
    public void onRemoval() {
        ArrayList<Integer> valid = new ArrayList();
        valid.addAll(ContainerCarpenter.inputSlots);
        valid.add(ContainerCarpenter.outputSlot);
        for (Integer i : Collections.unmodifiableList(valid)) {
            if (stacks[i] != null) {
                this.getWorldObj().spawnEntityInWorld(new EntityItem(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord, stacks[i]));
            }
        }
    }

    @Override
    public void setPreview(ItemStack stack) {
        this.setInventorySlotContents(ContainerCarpenter.previewSlot, stack);
    }

    @Override
    public void setGhostSlot(int index, ItemStack stack) {
        this.setInventorySlotContents(index, stack);
        this.getWorldObj().markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public int[] getOutputSlotNumbers() {
        return new int[]{ContainerCarpenter.outputSlot};
    }

    @Override
    public int[] getInputSlotForTop() {
        return ArrayUtils.toPrimitive(ContainerCarpenter.inputSlots.toArray(new Integer[ContainerCarpenter.inputSlots.size()]));
    }

    @Override
    public void onSlotChanged(int slot) {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack stack) {
        if (ContainerCarpenter.inputSlots.contains(i)) {
            return true;
        }
        if (ContainerCarpenter.outputSlot == i){
            return true;
        }
        return false;
    }
}
