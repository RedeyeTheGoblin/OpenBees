package info.inpureprojects.OpenBees.Integration;

import cpw.mods.fml.common.registry.LanguageRegistry;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.TileApiary;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by den on 8/11/2014.
 */
public class WailaApiaryData implements IWailaDataProvider {

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return OpenBeesAPI.getAPI().getCommonAPI().blocks.apiary.getStack(1);
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> strings, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return strings;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> strings, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        switch (iWailaDataAccessor.getMetadata()) {
            case 0:
                strings.add(LanguageRegistry.instance().getStringLocalization("status.openbees." + ((TileApiary) iWailaDataAccessor.getTileEntity()).getStatusCode()));
                break;
        }
        return strings;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> strings, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return strings;
    }
}
