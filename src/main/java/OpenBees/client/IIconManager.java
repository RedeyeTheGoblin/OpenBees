package OpenBees.client;

import net.minecraft.util.IIcon;

public interface IIconManager {

    public void registerIcon(String domain, String path, String tag);

    public void registerTexture(String domain, String path, String tag);

    public IIcon getIcon(String tag);
}
