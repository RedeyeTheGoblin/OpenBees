package OpenBees.utility.craftingHelpers;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class extractorRecipe implements IExtractorRecipe {


    public ItemStack inputStack;

    private List<combProduct> output;
    private int delay;

    public extractorRecipe(ItemStack input, List<combProduct> output, int time) {
        inputStack = input;

        this.delay = time;
        this.output = output;
    }

    @Override
    public int getRecipeDelayInSeconds() {
        return this.delay;
    }

    public boolean areMatching(ItemStack one, ItemStack two) {
        boolean match = false;

        if (one == null && two == null) {
            match = true;
        } else if (one == null || two == null) {
            match = false;
        } else {
            match = one.getItem() == two.getItem();
        }

        return match;
    }

    public boolean isEqual(extractorRecipe recipe) {
        return areMatching(inputStack, recipe.inputStack);
    }

    @Override
    public boolean doesMatch(ItemStack input) {
        return isEqual(new extractorRecipe(input, null, 1));
    }

    @Override
    public List<combProduct> getResult() {
        return this.output;
    }

    @Override
    public ItemStack getInput() {
        return inputStack;
    }
}
