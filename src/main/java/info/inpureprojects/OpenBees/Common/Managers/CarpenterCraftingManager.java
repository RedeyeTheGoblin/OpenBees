package info.inpureprojects.OpenBees.Common.Managers;

import info.inpureprojects.OpenBees.API.Common.ICarpenterManager;
import info.inpureprojects.OpenBees.Client.Gui.ContainerCarpenter;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.TileCarpenter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;

/**
 * Created by den on 8/16/2014.
 */
public class CarpenterCraftingManager implements ICarpenterManager {

    private static final CarpenterCraftingManager instance = new CarpenterCraftingManager();
    private ArrayList<ICarpenterRecipe> recipes = new ArrayList();

    public static CarpenterCraftingManager getInstance() {
        return instance;
    }

    public void register(ICarpenterRecipe recipe) {
        recipes.add(recipe);
    }

    public ArrayList<ICarpenterRecipe> getRecipes() {
        return recipes;
    }

    public boolean validateRecipe(TileCarpenter tile) {
        Object[] o = new Object[9];
        for (int i = 0; i < o.length; i++) {
            o[i] = tile.getStackInSlot(ContainerCarpenter.ghostSlots.get(i));
        }
        boolean foundMatch = false;
        for (ICarpenterRecipe r : CarpenterCraftingManager.getInstance().getRecipes()) {
            if (r.doesMatch(o, tile.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid)) {
                tile.setPreview(r.getResult());
                tile.setCurrentRecipe(r);
                foundMatch = true;
            }
        }
        if (!foundMatch) {
            tile.setPreview(null);
            tile.setCurrentRecipe(null);
        }
        return foundMatch;
    }

    @Override
    public void register(Object[] grid, FluidStack fluid, ItemStack output, int time) {
        this.register(new CarpenterRecipeOreDict(grid, fluid, output, time));
    }
}
