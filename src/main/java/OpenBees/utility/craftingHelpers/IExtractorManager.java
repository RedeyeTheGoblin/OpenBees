package OpenBees.utility.craftingHelpers;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface IExtractorManager {

    public void register(ItemStack input, List<combProduct> output, int time);
}
