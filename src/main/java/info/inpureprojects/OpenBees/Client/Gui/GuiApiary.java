package info.inpureprojects.OpenBees.Client.Gui;

import cofh.core.gui.GuiBaseAdv;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/**
 * Created by den on 8/7/2014.
 */
public class GuiApiary extends GuiBaseAdv {

    public GuiApiary(Container container, ResourceLocation resourceLocation) {
        super(container, resourceLocation);
        this.ySize = this.ySize + 10;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int i2) {

    }
}
