package info.inpureprojects.OpenBees.Client.Gui;

import cofh.core.gui.element.TabEnergy;
import cofh.lib.gui.element.ElementFluidTank;
import info.inpureprojects.OpenBees.Client.Gui.Elements.ElementProgressArrowHorizontal;
import info.inpureprojects.OpenBees.Client.Gui.Slots.SlotGhost;
import info.inpureprojects.OpenBees.Common.ModuleOpenBees;
import info.inpureprojects.OpenBees.Common.Network.Messages.GhostSlotClicked;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Created by den on 8/13/2014.
 */
public class GuiCarpenter extends GuiDenBase {

    private ContainerCarpenter container;
    private ElementProgressArrowHorizontal arrow;

    public GuiCarpenter(Container container, ResourceLocation resourceLocation) {
        super(container, resourceLocation);
        this.xSize = 176;
        this.ySize = 200;
        this.container = (ContainerCarpenter) container;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.addElement(new ElementFluidTank(this, 152, 8, this.container.tile.getTank()).setGauge(1));
        this.addTab(new TabEnergy(this, this.container.tile, false));
        arrow = new ElementProgressArrowHorizontal(this, 102, 57);
        this.addElement(arrow);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int i2) {
        if (this.container.tile.getRecipeTime() > 0) {
            float d = (float) this.container.tile.getRecipeProgess() / (float) this.container.tile.getRecipeTime();
            float t = 8 * d;
            this.arrow.setArrowProgress(Math.min((int) t, 8));
        } else {
            this.arrow.setArrowProgress(Math.min(0, 8));
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        Slot s = this.getSlotAtPosition(x, y);
        if (s instanceof SlotGhost) {
            if (button != 1 && Minecraft.getMinecraft().thePlayer.inventory.getItemStack() != null) {
                ItemStack c = Minecraft.getMinecraft().thePlayer.inventory.getItemStack().copy();
                c.stackSize = 1;
                ModuleOpenBees.networking.wrapper.sendToServer(new GhostSlotClicked(this.container.tile, s.getSlotIndex(), c));
                s.putStack(c);
                this.container.onCraftMatrixChanged(null);
            } else {
                ModuleOpenBees.networking.wrapper.sendToServer(new GhostSlotClicked(this.container.tile, s.getSlotIndex(), null));
                s.putStack(null);
                this.container.onCraftMatrixChanged(null);
            }
        }
    }
}
