package info.inpureprojects.OpenBees.Client.Gui;

import cofh.gui.GuiBase;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.Client.Gui.Tabs.TabStatus;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

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
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(this.texture);
        this.drawTexturedModalRect(22, 38, 176, 0, 6, 7 - this.container.getTile().getBreedingProgress() / 20);
        this.drawTexturedModalRect(38, 19, 182, 0, 1, this.container.getTile().getLifeProgress());
    }

    @Override
    public IIcon getIcon(String s) {
        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon(s);
    }
}
