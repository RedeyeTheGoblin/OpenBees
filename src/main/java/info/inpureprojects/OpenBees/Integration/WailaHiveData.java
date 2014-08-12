package info.inpureprojects.OpenBees.Integration;

import cpw.mods.fml.common.registry.LanguageRegistry;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.ISpecies;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by den on 8/11/2014.
 */
public class WailaHiveData implements IWailaDataProvider {

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return OpenBeesAPI.getAPI().getCommonAPI().blocks.beehive.getStack(1);
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        List<ISpecies> potential = OpenBeesAPI.getAPI().getCommonAPI().beeManager.getSpeciesForBiome(accessor.getWorld().getBiomeGenForCoords(accessor.getPosition().blockX, accessor.getPosition().blockZ));
        for (ISpecies s : potential) {
            currenttip.add(LanguageRegistry.instance().getStringLocalization(s.getUnlocalizedName()));
        }
        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }
}
