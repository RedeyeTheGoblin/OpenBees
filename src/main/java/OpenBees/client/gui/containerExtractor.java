package OpenBees.client.gui;

import OpenBees.block.tileEntities.tileExtractor;
import OpenBees.client.gui.slots.slotComb;
import OpenBees.client.gui.slots.slotOutput;
import OpenBees.client.imageScanner;
import OpenBees.client.pixelData;
import OpenBees.utility.logHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraftforge.common.util.ForgeDirection;
import scala.Int;
import scala.actors.threadpool.Arrays;

import java.util.List;


public class containerExtractor extends containerBase {

    public static final int inputSlot = 3;
    public static final int[]outputSlots = new int[]{0,1,2,4,5,6,7,8,9};

    public tileExtractor tile;
    private int lastRF = 0;
    private int lastProgress = 0;
    private int lastTime =0;
    private int lastRFt =0;

    public containerExtractor(EntityPlayer player, tileExtractor tile){
        super(player, 0, -6, 0, 0, 10);
        this.tile = tile;
        imageScanner scanner = new imageScanner();
        scanner.load("assets/openbees/textures/gui/gui_extractor_map.png");
        int index = 0;

        for (pixelData data : scanner.findTargets()) {

            if (index == 3) {
                this.addSlotToContainer(new slotComb(tile, index++, data.getX(), data.getY()));
            } else {
                this.addSlotToContainer(new slotOutput(tile, index++, data.getX(), data.getY()));
            }
        }
    }

    public tileExtractor getTile() {
        return tile;
    }

    @Override
    public void addCraftingToCrafters(ICrafting player) {
        super.addCraftingToCrafters(player);

        player.sendProgressBarUpdate(this, 200, this.tile.getEnergyStored(ForgeDirection.UNKNOWN));
        player.sendProgressBarUpdate(this, 0, this.tile.getRecipeProgress());
        player.sendProgressBarUpdate(this, 1, this.tile.getRecipeTime());
        player.sendProgressBarUpdate(this, 2, this.tile.getInfoEnergyPerTick());
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (ICrafting i: (List<ICrafting>) this.crafters) {
            if (lastRF != this.tile.getEnergyStored(ForgeDirection.UNKNOWN)) {
                i.sendProgressBarUpdate(this, 200, this.tile.getEnergyStored(ForgeDirection.UNKNOWN));
            }
            if (this.tile.getRecipeProgress() != this.lastProgress) {
                i.sendProgressBarUpdate(this, 0, this.tile.getRecipeProgress());
            }
            if (this.tile.getRecipeTime() != this.lastTime) {
                i.sendProgressBarUpdate(this, 1, this.tile.getRecipeTime());
            }
            if (this.tile.getInfoEnergyPerTick() != this.lastRFt) {
                i.sendProgressBarUpdate(this, 2, this.tile.getInfoEnergyPerTick());
            }
        }

        this.lastRF = this.tile.getEnergyStored(ForgeDirection.UNKNOWN);
        this.lastProgress = this.tile.getRecipeProgress();
        this.lastTime = this.tile.getRecipeTime();
        this.lastRFt = this.tile.getInfoEnergyPerTick();
    }

    @Override
    public void updateProgressBar(int code, int data) {
        super.updateProgressBar(code, data);
        switch (code) {
            case 0:
                this.tile.setRecipeProgress(data);
                break;

            case 1:
                this.tile.setRecipeTime(data);
                break;

            case 2:
                this.tile.setCurrentRF(data);
                break;

            case 200:
                this.tile.geteStorage().setEnergyStored(data);
                break;
        }
    }
}
