package OpenBees.init;

import OpenBees.OpenBees;
import OpenBees.utility.craftingHelpers.combProduct;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import scala.actors.threadpool.Arrays;

public class modRecipes {

    public static void init() {
        registerShaped();
        registerShapeless();
        registerExtractor();
    }

    public static void registerShaped() {
        ItemStack stickStack = new ItemStack(Items.stick);
        ItemStack stringStack = new ItemStack(Items.string);
        ItemStack ironStack = new ItemStack(Items.iron_ingot);
        ItemStack diamondStack = new ItemStack(Items.diamond);

        GameRegistry.addShapedRecipe(new ItemStack(OpenBees.items.scoop_wood.getItem()),
                "SWS",
                "SSS",
                " S ",
                'S', stickStack, 'W', stringStack);


        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(OpenBees.items.scoop_iron.getItem()),
                "SWS",
                "SIS",
                " S ",
                'S', stickStack, 'W', stringStack, 'I', "ingotIron"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(OpenBees.items.scoop_diamond.getItem()),
                "SWS",
                "SDS",
                " S ",
                'S', stickStack, 'W', stringStack, 'D', "gemDiamond"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(OpenBees.blocks.apiary.getBlock()),
                "SSS",
                "WDW",
                "WWW", 'S', "slabWood", 'W', "plankWood", 'D', "beeComb"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(OpenBees.blocks.extractor.getBlock()),
                ""));
    }

    public static void registerShapeless() {

    }

    public static void registerExtractor() {
        OpenBees.instance.extractorRecipes.register(OpenBees.items.honey_comb.getStack(1, 0), Arrays.asList(new combProduct[]{new combProduct(OpenBees.items.bee_drop.getStack(1, 0), 1.0f)}), 10);
        OpenBees.instance.extractorRecipes.register(OpenBees.items.honey_comb.getStack(1, 1), Arrays.asList(new combProduct[]{new combProduct(new ItemStack(Items.dye,1,3), 1.0f)}), 10);
        OpenBees.instance.extractorRecipes.register(OpenBees.items.honey_comb.getStack(1, 2), Arrays.asList(new combProduct[]{new combProduct(OpenBees.items.bee_drop.getStack(1, 0), 1.0f)}), 10);
        OpenBees.instance.extractorRecipes.register(OpenBees.items.honey_comb.getStack(1, 3), Arrays.asList(new combProduct[]{new combProduct(OpenBees.items.bee_drop.getStack(1, 2), 1.0f)}), 10);
        OpenBees.instance.extractorRecipes.register(OpenBees.items.honey_comb.getStack(1, 4), Arrays.asList(new combProduct[]{new combProduct(OpenBees.items.bee_drop.getStack(1, 3), 1.0f)}), 10);
    }
}
