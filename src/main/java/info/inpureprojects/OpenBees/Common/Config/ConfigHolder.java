package info.inpureprojects.OpenBees.Common.Config;

import info.inpureprojects.OpenBees.Common.NeedsMovedToCore;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by den on 8/6/2014.
 */
@NeedsMovedToCore
public class ConfigHolder {

    private File configFolder;
    private Configuration config;

    public ConfigHolder(File configFolder, String fileName) {
        this.configFolder = configFolder;
        this.configFolder.mkdirs();
        this.config = new Configuration(new File(configFolder, fileName));
        this.config.load();
        this.config.save();
    }

    public Configuration getConfig() {
        return config;
    }

    public File getConfigFolder() {
        return configFolder;
    }
}
