package info.inpureprojects.OpenBees.API.Common.Bees;

import info.inpureprojects.OpenBees.API.Common.Tools.IFrameItem;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by den on 8/7/2014.
 */
public interface IBeeLogic {

    public ItemStack combine(IBeeKeepingTile tile);

    public ItemStack produceOffspring(IBeeKeepingTile tile, List<IFrameItem> allModifiers, boolean princess);

    public List<ItemStack> produceItemsOnTick(IBeeKeepingTile tile);

}
