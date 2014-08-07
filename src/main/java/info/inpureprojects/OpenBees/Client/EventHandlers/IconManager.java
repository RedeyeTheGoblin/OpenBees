package info.inpureprojects.OpenBees.Client.EventHandlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import info.inpureprojects.OpenBees.API.Client.Managers.IIconManager;
import info.inpureprojects.OpenBees.Client.IconData;
import info.inpureprojects.OpenBees.Common.ModuleOpenBees;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by den on 8/6/2014.
 */
public class IconManager implements IIconManager {

    private HashMap<String, IIcon> icons = new HashMap();
    private ArrayList<IconData> data = new ArrayList();

    public IconManager() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void registerIcon(String domain, String path, String tag) {
        data.add(new IconData(domain, path, tag));
    }

    @Override
    public IIcon getIcon(String tag) {
        if (icons.containsKey(tag)) {
            return icons.get(tag);
        } else {
            return icons.get("radical_edward");
        }
    }

    @SubscribeEvent
    public void onEvent(TextureStitchEvent.Pre evt) {
        if (evt.map.getTextureType() == 1) {
            for (IconData d : data) {
                ResourceLocation l = new ResourceLocation(d.getDomain(), d.getPath());
                icons.put(d.getTag(), evt.map.registerIcon(l.toString()));
                ModuleOpenBees.proxy.print("Registered icon: " + l.toString());
            }
        }
    }
}
