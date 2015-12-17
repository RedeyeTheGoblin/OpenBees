package OpenBees.client.gui;

import OpenBees.block.tileEntities.tileApiary;
import OpenBees.client.gui.slots.*;
import OpenBees.client.imageScanner;
import OpenBees.client.pixelData;
import OpenBees.utility.logHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import java.util.List;

public class containerApiary extends containerBase {

    private tileApiary tile;
    private int lastStatusCode;
    private int lastBreedingProgress;
    private int lastLifeProgress;

    public containerApiary(EntityPlayer player, tileApiary tile)
    {
        super(player, 0, -6, 0, 0, 12);
        this.tile = tile;
        imageScanner scanner = new imageScanner();
        scanner.load("assets/openbees/textures/gui/gui_apiary_map.png");
        int index = 0;

        for (pixelData data : scanner.findTargets())
        {

            if (index == 3)
            {
                this.addSlotToContainer(new slotRoyal(tile, index++, data.getX(), data.getY()));
            } else if (index == 8)
            {
                this.addSlotToContainer(new slotDrone(tile, index++, data.getX(), data.getY()));
            } else if (index == 1 || index == 2 || index == 5 || index == 6 || index == 7 || index == 9 || index == 10 )
            {
                this.addSlotToContainer(new slotOutput(tile, index++, data.getX(), data.getY()));
            } else if (index == 0 || index == 4 || index == 11)
            {
                this.addSlotToContainer(new slotFrame(tile, index++, data.getX(), data.getY()));
            } else
            {
                this.addSlotToContainer(new Slot(tile, index++, data.getX(), data.getY()));
            }
        }
    }

    public tileApiary getTile()
    {
        return tile;
    }

    @Override
    public void addCraftingToCrafters(ICrafting player)
    {
        super.addCraftingToCrafters(player);
        player.sendProgressBarUpdate(this, 0, tile.getStatusCode());
        player.sendProgressBarUpdate(this, 1, tile.getBreedingProgress());
        player.sendProgressBarUpdate(this, 2, tile.getLifeProgress());
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for (ICrafting i: (List<ICrafting>) this.crafters)
        {
            if (this.lastStatusCode != tile.getStatusCode())
            {
                i.sendProgressBarUpdate(this, 0, tile.getStatusCode());
            }

            if (this.lastBreedingProgress != tile.getBreedingProgress())
            {
                i.sendProgressBarUpdate(this, 1, tile.getBreedingProgress());
            }

            if (this.lastLifeProgress != tile.getLifeProgress())
            {
                i.sendProgressBarUpdate(this, 2, tile.getLifeProgress());
            }
        }
        this.lastStatusCode = tile.getStatusCode();
        this.lastBreedingProgress = tile.getBreedingProgress();
        this.lastLifeProgress = tile.getLifeProgress();
    }

    @Override
    public void updateProgressBar(int c, int d)
    {
        super.updateProgressBar(c, d);

        switch (c)
        {
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
