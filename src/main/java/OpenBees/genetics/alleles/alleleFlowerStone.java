package OpenBees.genetics.alleles;

import OpenBees.genetics.IBee;
import OpenBees.genetics.alleleHandlers.alleleFlowerHandler;
import OpenBees.utility.blockIdentifier;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class alleleFlowerStone extends alleleFlowerHandler {

    private ArrayList<blockIdentifier> validBlocks = new ArrayList();

    public alleleFlowerStone()
    {
        super("Flower.STONE", null, 0);
    }

    @Override
    public boolean isValid(World world, int x, int y, int z, IBee bee)
    {
        for (ItemStack iStack : OreDictionary.getOres("stone"))
        {
            blockIdentifier blockID = new blockIdentifier(GameRegistry.findUniqueIdentifierFor(Block.getBlockFromItem(iStack.getItem())).toString(), iStack.getItemDamage());

            if (validBlocks.contains(blockID))
            {
                return true;
            }
        }
        return false;
    }
}
