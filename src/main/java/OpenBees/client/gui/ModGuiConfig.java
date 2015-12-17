package OpenBees.client.gui;

import OpenBees.info.modInfo;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import OpenBees.handler.configurationHandler;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ModGuiConfig extends GuiConfig {

    public ModGuiConfig(GuiScreen guiScreen) {
        super(guiScreen,
                new ConfigElement(configurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                modInfo.modid,
                "",
                false,
                false,
                GuiConfig.getAbridgedConfigPath(configurationHandler.configuration.toString()));
    }
}
