package info.inpureprojects.OpenBees.Client.Gui;

import info.inpureprojects.OpenBees.Client.Gui.Slots.SlotDrone;
import info.inpureprojects.OpenBees.Client.Gui.Slots.SlotFrame;
import info.inpureprojects.OpenBees.Client.Gui.Slots.SlotOutput;
import info.inpureprojects.OpenBees.Client.Gui.Slots.SlotPrincessOrQueen;
import info.inpureprojects.OpenBees.Client.ImageScanner;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.TileApiary;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import java.util.List;

/**
 * Created by den on 8/7/2014.
 */
public class ContainerApiary extends ContainerBase {


    private TileApiary tile;
    private int lastStatusCode = 0;
    private int lastBreedingProgress = 0;
    private int lastLifeProgress = 0;

    public ContainerApiary(EntityPlayer player, TileApiary tile) {
        super(player, 0, -6, 0, 0, 12);
        this.tile = tile;
        ImageScanner i = new ImageScanner();
        i.load("assets/openbees/textures/gui/gui_apiary_map.png");
        int index = 0;
        for (ImageScanner.PixelData d : i.findTargets()) {
            if (index == 3) {
                this.addSlotToContainer(new SlotPrincessOrQueen(tile, index++, d.getX(), d.getY()));
            } else if (index == 8) {
                this.addSlotToContainer(new SlotDrone(tile, index++, d.getX(), d.getY()));
            } else if (index == 1 || index == 2 || index == 5 || index == 6 || index == 7 || index == 9 || index == 10) {
                this.addSlotToContainer(new SlotOutput(tile, index++, d.getX(), d.getY()));
            } else if (index == 0 || index == 4 || index == 11) {
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
        player.sendProgressBarUpdate(this, 1, tile.getBreedingProgress());
        player.sendProgressBarUpdate(this, 2, tile.getLifeProgress());
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (ICrafting i : (List<ICrafting>) this.crafters) {
            if (this.lastStatusCode != tile.getStatusCode()) {
                i.sendProgressBarUpdate(this, 0, tile.getStatusCode());
            }
            if (this.lastBreedingProgress != tile.getBreedingProgress()) {
                i.sendProgressBarUpdate(this, 1, tile.getBreedingProgress());
            }
            if (this.lastLifeProgress != tile.getLifeProgress()) {
                i.sendProgressBarUpdate(this, 2, tile.getLifeProgress());
            }
        }
        this.lastStatusCode = tile.getStatusCode();
        this.lastBreedingProgress = tile.getBreedingProgress();
    }

    @Override
    public void updateProgressBar(int c, int d) {
        super.updateProgressBar(c, d);
        switch (c) {
            case 0:
                tile.setStatusCode(d);
                break;
            case 1:
                tile.setBreedingProgress(d);
                break;
            case 2:
                tile.setLifeProgress(d);
                break;
        }
    }

}
