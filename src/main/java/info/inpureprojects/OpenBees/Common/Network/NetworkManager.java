package info.inpureprojects.OpenBees.Common.Network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import info.inpureprojects.OpenBees.API.modInfo;
import info.inpureprojects.OpenBees.Common.Network.Messages.GhostSlotClicked;
import info.inpureprojects.OpenBees.Common.Network.Messages.UpdatePreviewSlot;

/**
 * Created by den on 8/15/2014.
 */
public class NetworkManager {

    public SimpleNetworkWrapper wrapper;

    public NetworkManager() {
        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(modInfo.modid);
        wrapper.registerMessage(GhostSlotClicked.class, GhostSlotClicked.class, 0, Side.SERVER);
        wrapper.registerMessage(UpdatePreviewSlot.class, UpdatePreviewSlot.class, 1, Side.SERVER);
    }

}
