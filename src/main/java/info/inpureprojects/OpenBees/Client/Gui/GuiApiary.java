package info.inpureprojects.OpenBees.Client.Gui;

import cofh.gui.GuiBase;
import cofh.gui.element.TabBase;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.Client.Gui.Tabs.TabStatus;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

/**
 * Created by den on 8/7/2014.
 */
public class GuiApiary extends GuiBase {

    private TabStatus status;
    private ContainerApiary container;
    private int[] colors = new int[]{0x00FF00, 0xFFFF00, 0xFF0000};

    public GuiApiary(Container container, ResourceLocation resourceLocation) {
        super(container, resourceLocation);
        this.container = (ContainerApiary) container;
    }

    @Override
    public void initGui() {
        super.initGui();
        status = new TabStatus(this);
        this.addTab(status);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int i2) {
        status.backgroundColor = colors[container.getTile().getStatusCode()];
    }

    @Override
    public IIcon getIcon(String s) {
        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon(s);
    }
}
