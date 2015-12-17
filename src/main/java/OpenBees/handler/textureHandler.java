package OpenBees.handler;

import OpenBees.client.IIconManager;
import OpenBees.client.iconData;
import OpenBees.utility.logHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.HashMap;

public class textureHandler implements IIconManager {

    private HashMap<String, IIcon> icons = new HashMap();
    private ArrayList<iconData> data = new ArrayList();

    public textureHandler()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void registerIcon(String domain, String path, String tag)
    {
        data.add(new iconData(domain, path, tag, false));
    }

    @Override
    public IIcon getIcon(String tag)
    {
        if (icons.containsKey(tag))
        {
            return icons.get(tag);
        } else
        {
            return null;
        }
    }

    @Override
    public void registerTexture(String domain, String path, String tag)
    {
        data.add(new iconData(domain, path, tag, true));
    }

    @SubscribeEvent
    @Mod.EventHandler
    public void onEvent(TextureStitchEvent.Pre event)
    {
        if (event.map.getTextureType() == 1)
        {
            for (iconData iData : data)
            {
                if (!iData.isBlock())
                {
                    ResourceLocation location = new ResourceLocation(iData.getDomain(), iData.getPath());
                    icons.put(iData.getTag(), event.map.registerIcon(location.toString()));
                }
            }
        } else
        {
            for (iconData iconData : data)
            {
                if (iconData.isBlock())
                {
                    ResourceLocation location = new ResourceLocation(iconData.getDomain(), iconData.getPath());
                    icons.put(iconData.getTag(), event.map.registerIcon(location.toString()));
                }
            }
        }
    }
}
