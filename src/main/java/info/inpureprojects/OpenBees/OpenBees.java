package info.inpureprojects.OpenBees;

import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.API.modInfo;
import info.inpureprojects.OpenBees.Common.Events.EventSetupBlocks;
import info.inpureprojects.OpenBees.Common.Events.EventSetupItems;
import info.inpureprojects.core.API.Events.EventPreloaderRegister;
import info.inpureprojects.core.API.PreloaderAPI;

/**
 * Created by den on 8/6/2014.
 */
@Mod(modid = modInfo.modid, name = modInfo.name, version = modInfo.version, dependencies = modInfo.deps)
public class OpenBees {

    /*
    Nothing else should go in this class. This is just to get a kick-start from FML.
     */

    @Mod.Instance
    public static OpenBees instance;

    public OpenBees() {
        PreloaderAPI.preLoaderEvents.register(this);
    }

    @Subscribe
    public void onEvent(EventPreloaderRegister evt) {
        PreloaderAPI.modules.register("info.inpureprojects.OpenBees.Common.ModuleOpenBees");
        if (Loader.isModLoaded("Waila")) {
            PreloaderAPI.modules.register("info.inpureprojects.OpenBees.Integration.ModuleWaila");
        }
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent evt) {
        // Needed to do it this way so the items show up as being from OpenBees and not INpureCore.
        OpenBeesAPI.getAPI().getCommonAPI().events.post(new EventSetupBlocks());
        OpenBeesAPI.getAPI().getCommonAPI().events.post(new EventSetupItems());
    }

}
