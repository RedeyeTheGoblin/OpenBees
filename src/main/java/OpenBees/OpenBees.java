package OpenBees;

import OpenBees.client.gui.guiHandler;
import OpenBees.events.forgeHandler;
import OpenBees.genetics.Allele;
import OpenBees.handler.beeHandler;
import OpenBees.handler.configurationHandler;
import OpenBees.handler.textureHandler;
import OpenBees.init.*;
import OpenBees.proxy.IProxy;
import OpenBees.info.modInfo;
import OpenBees.proxy.clientProxy;
import OpenBees.utility.beeHelper;
import OpenBees.utility.blockRegistry;
import OpenBees.utility.itemRegistry;
import OpenBees.utility.logHelper;
import OpenBees.worldgen.worldGenHives;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;

import java.util.Map;

@Mod(modid = modInfo.modid, name = modInfo.modname, version = modInfo.modversion, guiFactory = modInfo.GuiFactoryClass)
public class OpenBees
{

    @Mod.Instance (modInfo.modid)
    public static OpenBees instance = new OpenBees();
    public static beeHandler coreBeeHandler = new beeHandler();
    public static beeHelper coreBeeHelper = new beeHelper();
    public static textureHandler coreTexHandler = new textureHandler();
    public static itemRegistry items = new itemRegistry();
    public static blockRegistry blocks = new blockRegistry();

    @SidedProxy(clientSide = modInfo.clientproxyname, serverSide = modInfo.serverproxyname)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        configurationHandler.init(event.getSuggestedConfigurationFile());
        modIcons.init();
        FMLCommonHandler.instance().bus().register(new configurationHandler());
        MinecraftForge.EVENT_BUS.register(new forgeHandler());

        NetworkRegistry.INSTANCE.registerGuiHandler(OpenBees.instance, new guiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        modBlocks.init();
        modItems.init();
        modAlleles.init();
        modBees.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        modBees.registerMutations();
        worldGenHives.init();
    }

}
