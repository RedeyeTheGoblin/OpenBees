package OpenBees.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class configurationHandler {
    public static Configuration configuration;
    public static boolean configValue = false;

    public static void init(File configFile) {

        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }

    }

    @SubscribeEvent
    public void OnConfigurationChangedEvent(ConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(OpenBees.info.modInfo.modid)) {
            loadConfiguration();
        }
    }

    public static void loadConfiguration() {
        configValue = configuration.getBoolean("configValue", Configuration.CATEGORY_GENERAL, false, "Testing config values!");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
