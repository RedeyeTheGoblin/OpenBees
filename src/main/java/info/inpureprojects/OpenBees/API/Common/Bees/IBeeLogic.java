package info.inpureprojects.OpenBees.API.Common.Bees;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by den on 8/7/2014.
 */
public interface IBeeLogic {

    public ItemStack combine(ItemStack princess, ItemStack drone);

    public ItemStack produceOffspring(ItemStack queen, boolean princess);

    public List<ItemStack> produceItemsOnTick(ItemStack queen, int tick);

}
