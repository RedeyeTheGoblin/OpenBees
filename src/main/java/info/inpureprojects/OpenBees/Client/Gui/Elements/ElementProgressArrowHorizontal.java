package info.inpureprojects.OpenBees.Client.Gui.Elements;

import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.ElementBase;
import info.inpureprojects.OpenBees.API.modInfo;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by den on 8/16/2014.
 */
public class ElementProgressArrowHorizontal extends ElementBase {

    private int arrowProgress = 0;

    public ElementProgressArrowHorizontal(GuiBase guiBase, int i, int i2) {
        super(guiBase, i, i2);
        this.texture = new ResourceLocation(modInfo.modid, "textures/gui/gui_carpenter.png");
    }

    public int getArrowProgress() {
        return arrowProgress;
    }

    public void setArrowProgress(int arrowProgress) {
        this.arrowProgress = arrowProgress;
    }

    @Override
    public void drawBackground(int i, int i2, float v) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.gui.mc.getTextureManager().bindTexture(this.texture);
        this.drawTexturedModalRect(this.posX, this.posY, 176, 0, this.arrowProgress, 8);
    }

    @Override
    public void drawForeground(int i, int i2) {

    }
}
