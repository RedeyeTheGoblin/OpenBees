package info.inpureprojects.OpenBees.Integration;

import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.core.API.IINpureSubmodule;
import mcp.mobius.waila.api.impl.ModuleRegistrar;

import java.io.File;

/**
 * Created by den on 8/11/2014.
 */
public class ModuleWaila implements IINpureSubmodule {

    @Override
    public void pre(File configFolder) {

    }

    @Override
    public void init() {

    }

    @Override
    public void post() {
        ModuleRegistrar.instance().registerBodyProvider(new WailaHiveData(), OpenBeesAPI.getAPI().getCommonAPI().blocks.beehive.getBlock().getClass());
        ModuleRegistrar.instance().registerBodyProvider(new WailaApiaryData(), OpenBeesAPI.getAPI().getCommonAPI().blocks.apiary.getBlock().getClass());
    }
}
