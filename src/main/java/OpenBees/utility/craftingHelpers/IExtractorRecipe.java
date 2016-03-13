package OpenBees.utility.craftingHelpers;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface IExtractorRecipe {

    public boolean doesMatch(ItemStack input);

    public List<combProduct> getResult();

    public int getRecipeDelayInSeconds();

    public ItemStack getInput();
}
