package info.inpureprojects.OpenBees.Client.Gui.Tabs;

import cofh.gui.GuiBase;
import cofh.gui.element.TabBase;

/**
 * Created by den on 8/8/2014.
 */
public class TabStatus extends TabBase {

    public TabStatus(GuiBase guiBase) {
        super(guiBase);
        this.backgroundColor = 0x00CCFF;
    }

    @Override
    public void draw() {
        drawBackground();
        drawTabIcon("scoop");
        if (!isFullyOpened()) {
            return;
        }
    }
}
