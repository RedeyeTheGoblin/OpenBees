package info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by den on 8/6/2014.
 */
public class AlleleFlower extends Allele {

    private Block requiredBlock;
    private int meta;

    public AlleleFlower(String tag, Block requiredBlock, int meta) {
        super(tag);
        this.requiredBlock = requiredBlock;
        this.meta = meta;
    }

    public boolean isValid(World world, int x, int y, int z, ItemStack bee) {
        if (GameRegistry.findUniqueIdentifierFor(world.getBlock(x, y, z)).equals(GameRegistry.findUniqueIdentifierFor(this.requiredBlock)) && world.getBlockMetadata(x, y, z) == this.meta) {
            return true;
        }
        return false;
    }
}
