package info.inpureprojects.OpenBees.Client.EventHandlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import info.inpureprojects.OpenBees.API.Client.IconData;
import info.inpureprojects.OpenBees.API.Client.Managers.IIconManager;
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
        data.add(new IconData(domain, path, tag, false));
    }

    @Override
    public IIcon getIcon(String tag) {
        if (icons.containsKey(tag)) {
            return icons.get(tag);
        } else {
            return icons.get("radical_edward");
        }
    }

    @Override
    public void registerTexture(String domain, String path, String tag) {
        data.add(new IconData(domain, path, tag, true));
    }

    @SubscribeEvent
    public void onEvent(TextureStitchEvent.Pre evt) {
        if (evt.map.getTextureType() == 1) {
            for (IconData d : data) {
                if (!d.isBlock()) {
                    ResourceLocation l = new ResourceLocation(d.getDomain(), d.getPath());
                    icons.put(d.getTag(), evt.map.registerIcon(l.toString()));
                }
            }
        } else {
            for (IconData d : data) {
                if (d.isBlock()) {
                    ResourceLocation l = new ResourceLocation(d.getDomain(), d.getPath());
                    icons.put(d.getTag(), evt.map.registerIcon(l.toString()));
                }
            }
        }
    }
}
