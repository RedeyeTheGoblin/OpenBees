package info.inpureprojects.OpenBees.API.Client.Managers;

import net.minecraft.util.IIcon;

/**
 * Created by den on 8/6/2014.
 */
public interface IIconManager {

    public void registerIcon(String domain, String path, String tag);

    public void registerTexture(String domain, String path, String tag);

    public IIcon getIcon(String tag);

}
