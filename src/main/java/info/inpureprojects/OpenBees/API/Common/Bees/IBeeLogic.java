package info.inpureprojects.OpenBees.API.Common.Bees;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by den on 8/7/2014.
 */
public interface IBeeLogic {

    public ItemStack combine(ItemStack princess, ItemStack drone);

    public List<ItemStack> produceOffspring(ItemStack queen);

    public List<ItemStack> produceItemsOnTick(ItemStack queen, int tick);

}
