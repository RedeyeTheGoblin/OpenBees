package OpenBees.client.gui;

import OpenBees.utility.logHelper;
import cofh.lib.gui.element.ElementEnergyStored;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class guiExtractor extends guiBaseCustom {

    private containerExtractor container;

    public guiExtractor (Container container, ResourceLocation resourceLocation) {
        super(container, resourceLocation);
        this.container = (containerExtractor) container;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.addElement(new ElementEnergyStored(this, 18, 18, container.getTile().geteStorage()));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int i2) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(this.texture);
        if (this.container.getTile().getRecipeProgress() > 0) {
            this.drawTexturedModalRect(44, 53, 176, 0, this.container.getTile().getRecipeProgress()/15, 1);
        }
    }
}
