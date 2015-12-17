package OpenBees.client.gui;

import OpenBees.OpenBees;
import OpenBees.client.gui.tabs.tabApiaryStatus;
import cofh.lib.gui.GuiBase;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class guiApiary extends GuiBase {

    private tabApiaryStatus status;
    private containerApiary container;
    private int[] colours = new int[]{0x00FF00, 0xFFFF00, 0xFF0000, 0x000000, 0xCC3300, 0x0000FF, 0x66FFFF, 0xFF66FF};

    public guiApiary(Container container, ResourceLocation resourceLocation)
    {
        super(container, resourceLocation);
        this.container = (containerApiary) container;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        status = new tabApiaryStatus(this);
        this.addTab(status);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int i2)
    {
        status.backgroundColor = colours[container.getTile().getStatusCode()];
        status.setText("status.openbees." + String.valueOf(container.getTile().getStatusCode()));
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(this.texture);
        this.drawTexturedModalRect(22, 38, 176, 0, 6, 7 - this.container.getTile().getBreedingProgress() / 20);
        this.drawTexturedModalRect(38, 19, 182, 0, 1, this.container.getTile().getLifeProgress());
    }

    @Override
    public IIcon getIcon(String s)
    {
        return  OpenBees.coreTexHandler.getIcon(s);
    }
}
