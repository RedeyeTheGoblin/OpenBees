package info.inpureprojects.OpenBees.Client.Gui;

import cofh.lib.gui.GuiBase;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

/**
 * Created by den on 8/13/2014.
 */
public abstract class GuiDenBase extends GuiBase {

    public GuiDenBase(Container container, ResourceLocation resourceLocation) {
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
        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon(s);
    }
}
