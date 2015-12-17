package OpenBees.client.gui.tabs;

import OpenBees.info.modInfo;
import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.TabBase;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.util.IIcon;

import java.util.List;

public class tabApiaryStatus extends TabBase {

    private String text = "";

    public tabApiaryStatus(GuiBase guiBase)
    {
        super(guiBase);
        this.backgroundColor = 0x00ccff;
        this.maxHeight = 92;
        this.maxWidth = 100;
        this.textColor = 0xffffff;
    }

    @Override
    public void draw()
    {
        drawBackground();
        drawTabIcon("scoop");
        if (!isFullyOpened())
        {
            return;
        }
    }

    @Override
    public void addTooltip(List<String> strings)
    {
        super.addTooltip(strings);
        strings.add(LanguageRegistry.instance().getStringLocalization(this.text));
    }

    public void setText(String text)
    {
        this.text = text;
    }

}
