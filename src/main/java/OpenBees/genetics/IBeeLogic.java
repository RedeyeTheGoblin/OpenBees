package OpenBees.genetics;

import OpenBees.item.interfaces.IFrameItem;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface IBeeLogic {

    public ItemStack combine(IBeeKeepingTile tile);

    public ItemStack produceOffspring(IBeeKeepingTile tile, List<IFrameItem> allModifiers, boolean princess);

    public List<ItemStack> produceItemsOnTick(IBeeKeepingTile tile);
}
