package info.inpureprojects.OpenBees.Client.Gui;

import info.inpureprojects.OpenBees.Client.Gui.Slots.*;
import info.inpureprojects.OpenBees.Client.ImageScanner;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.TileCarpenter;
import info.inpureprojects.OpenBees.Common.ModuleOpenBees;
import info.inpureprojects.OpenBees.Common.Network.Messages.UpdatePreviewSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by den on 8/13/2014.
 */
public class ContainerCarpenter extends ContainerBase {

    public static final List<Integer> ghostSlots = Arrays.asList(new Integer[]{0, 1, 2, 4, 5, 6, 7, 8, 9});
    public static final List<Integer> inputSlots = Arrays.asList(new Integer[]{12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29});
    public static final int canSlot = 3;
    public static final int previewSlot = 10;
    public static final int outputSlot = 11;

    public TileCarpenter tile;
    private FluidStack lastFluid;
    private int lastRF = 0;
    private int lastProgess = 0;
    private int lastTime = 0;
    private int lastRFt = 0;

    public ContainerCarpenter(EntityPlayer player, TileCarpenter tile) {
        super(player, 0, 28, 0, 34, 30);
        this.tile = tile;
        ImageScanner i = new ImageScanner();
        i.load("assets/openbees/textures/gui/gui_carpenter_map.png");
        HashMap<Integer, ImageScanner.PixelData> targets = new HashMap();
        int index = 0;
        for (ImageScanner.PixelData d : i.findTargets()) {
            targets.put(index++, d);
        }
        for (Integer id : ghostSlots) {
            this.addSlotToContainer(new SlotGhost(tile, id, targets.get(id).getX(), targets.get(id).getY()));
        }
        for (Integer id : inputSlots) {
            this.addSlotToContainer(new SlotStorage(tile, id, targets.get(id).getX(), targets.get(id).getY()));
        }
        this.addSlotToContainer(new SlotFluidContainer(tile, canSlot, targets.get(canSlot).getX(), targets.get(canSlot).getY()));
        this.addSlotToContainer(new SlotPreview(tile, previewSlot, targets.get(previewSlot).getX(), targets.get(previewSlot).getY()));
        this.addSlotToContainer(new SlotOutput(tile, outputSlot, targets.get(outputSlot).getX(), targets.get(outputSlot).getY()));
    }

    @Override
    public void onCraftMatrixChanged(IInventory p_75130_1_) {
        ModuleOpenBees.networking.wrapper.sendToServer(new UpdatePreviewSlot(this.tile));
    }

    @Override
    public void addCraftingToCrafters(ICrafting player) {
        super.addCraftingToCrafters(player);
        if (this.tile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid != null) {
            player.sendProgressBarUpdate(this, 100, this.tile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.fluidID);
            player.sendProgressBarUpdate(this, 101, this.tile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.amount);
        } else {
            player.sendProgressBarUpdate(this, 100, -1);
            player.sendProgressBarUpdate(this, 101, 0);
        }
        //
        player.sendProgressBarUpdate(this, 200, this.tile.getEnergyStored(ForgeDirection.UNKNOWN));
        player.sendProgressBarUpdate(this, 0, this.tile.getRecipeProgess());
        player.sendProgressBarUpdate(this, 1, this.tile.getRecipeTime());
        player.sendProgressBarUpdate(this, 2, this.tile.getInfoEnergyPerTick());
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        boolean nullFluid = false;
        for (ICrafting i : (List<ICrafting>) this.crafters) {
            if (this.tile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid != null) {
                if (!this.tile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.isFluidStackIdentical(this.lastFluid)) {
                    i.sendProgressBarUpdate(this, 100, this.tile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.fluidID);
                    i.sendProgressBarUpdate(this, 101, this.tile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.amount);
                }
            } else {
                nullFluid = true;
                i.sendProgressBarUpdate(this, 100, -1);
                i.sendProgressBarUpdate(this, 101, 0);
            }
            if (lastRF != this.tile.getEnergyStored(ForgeDirection.UNKNOWN)) {
                i.sendProgressBarUpdate(this, 200, this.tile.getEnergyStored(ForgeDirection.UNKNOWN));
            }
            if (this.tile.getRecipeProgess() != this.lastProgess) {
                i.sendProgressBarUpdate(this, 0, this.tile.getRecipeProgess());
            }
            if (this.tile.getRecipeTime() != this.lastTime) {
                i.sendProgressBarUpdate(this, 1, this.tile.getRecipeTime());
            }
            if (this.tile.getInfoEnergyPerTick() != this.lastRFt){
                i.sendProgressBarUpdate(this, 2, this.tile.getInfoEnergyPerTick());
            }
        }
        if (nullFluid) {
            this.lastFluid = null;
        } else {
            this.lastFluid = this.tile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid;
        }
        this.lastProgess = this.tile.getRecipeProgess();
        this.lastRF = this.tile.getEnergyStored(ForgeDirection.UNKNOWN);
        this.lastTime = this.tile.getRecipeTime();
        this.lastRFt = this.tile.getInfoEnergyPerTick();
    }

    @Override
    public void updateProgressBar(int c, int d) {
        super.updateProgressBar(c, d);
        switch (c) {
            case 100:
                if (d == -1) {
                    this.tile.getTank().setFluid(null);
                } else {
                    this.tile.getTank().setFluid(new FluidStack(FluidRegistry.getFluid(d), 0));
                }
                break;
            case 101:
                if (this.tile.getTank().getFluid() != null) {
                    this.tile.getTank().getFluid().amount = d;
                }
                break;
            case 200:
                this.tile.getStorage().setEnergyStored(d);
                break;
            case 0:
                this.tile.setRecipeProgess(d);
                break;
            case 1:
                this.tile.setRecipeTime(d);
                break;
            case 2:
                this.tile.setCurrentRF(d);
                break;
        }
    }
}
