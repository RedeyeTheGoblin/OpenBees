package info.inpureprojects.OpenBees.Proxy;

import cpw.mods.fml.common.FMLLog;
import info.inpureprojects.OpenBees.API.Common.DefaultValue;
import info.inpureprojects.OpenBees.API.IOpenBeesAPI;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.Common.Config.APIHandler;
import info.inpureprojects.OpenBees.Common.Config.ConfigHolder;
import info.inpureprojects.OpenBees.Common.Managers.BeeManager;
import info.inpureprojects.OpenBees.Common.ModuleOpenBees;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by den on 8/6/2014.
 */
public class ProxyCommon extends Proxy {

    public static IOpenBeesAPI apiHandler;

    @Override
    public void setupAPI() {
        apiHandler = new APIHandler();
        OpenBeesAPI.getAPI().getCommonAPI().beeManager = new BeeManager();
    }

    @Override
    public void setupConfig(File configFolder) {
        try {
            ModuleOpenBees.config = new ConfigHolder(new File(configFolder, "OpenBees"), "OpenBees.cfg");
            for (Field f : OpenBeesAPI.getAPI().getCommonAPI().settings.getClass().getDeclaredFields()) {
                String[] split = f.getName().split("_");
                DefaultValue v = f.getAnnotation(DefaultValue.class);
                Object set = null;
                if (f.getType().equals(boolean.class)) {
                    set = ModuleOpenBees.config.getConfig().get(split[0], split[1], Boolean.valueOf(v.value())).getBoolean();
                }
                f.set(OpenBeesAPI.getAPI().getCommonAPI().settings, set);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void print(String msg) {
        FMLLog.info("[OpenBees]: " + msg);
    }

    @Override
    public boolean isShiftKey() {
        return false;
    }

    @Override
    public boolean isShiftCtrlKey() {
        return false;
    }

    @Override
    public boolean isCtrlKey() {
        return false;
    }
}
