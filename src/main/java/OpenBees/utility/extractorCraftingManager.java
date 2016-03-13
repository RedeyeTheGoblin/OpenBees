package OpenBees.utility;

import OpenBees.OpenBees;
import OpenBees.block.tileEntities.tileExtractor;
import OpenBees.client.gui.containerExtractor;
import OpenBees.utility.craftingHelpers.IExtractorManager;
import OpenBees.utility.craftingHelpers.IExtractorRecipe;
import OpenBees.utility.craftingHelpers.combProduct;
import OpenBees.utility.craftingHelpers.extractorRecipe;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class extractorCraftingManager implements IExtractorManager {

    private ArrayList<IExtractorRecipe> recipes = new ArrayList();

    public void register(IExtractorRecipe recipe) {
        recipes.add(recipe);
    }

    public ArrayList<IExtractorRecipe> getRecipes() {
        return recipes;
    }

    public boolean validateRecipe(tileExtractor tile) {
        ItemStack inputStack = tile.getStackInSlot(containerExtractor.inputSlot);
        boolean foundMatch = false;
        for (IExtractorRecipe recipe : OpenBees.instance.extractorRecipes.getRecipes()) {
            if (recipe.doesMatch(inputStack)) {

                tile.setCurrentRecipe(recipe);
                foundMatch = true;
            }
        }
        if (!foundMatch) {
            tile.setCurrentRecipe(null);
        }
        return foundMatch;
    }

    @Override
    public void register(ItemStack input, List<combProduct> output, int time) {
        this.register(new extractorRecipe(input, output, time));
    }
}
