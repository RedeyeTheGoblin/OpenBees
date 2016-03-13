package OpenBees.client.gui;

import OpenBees.OpenBees;
import cofh.lib.gui.GuiBase;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public abstract class guiBaseCustom extends GuiBase {

    public guiBaseCustom (Container container, ResourceLocation resourceLocation) {
        super(container, resourceLocation);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    protected abstract void drawGuiContainerForegroundLayer(int i, int i2);

    @Override
    public IIcon getIcon(String s) {
        return OpenBees.coreTexHandler.getIcon(s);
    }
}
