package info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles;

import info.inpureprojects.OpenBees.API.Common.Bees.IBee;
import net.minecraft.block.Block;
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

    public boolean isValid(World world, int x, int y, int z, IBee queen) {
        if (requiredBlock == world.getBlock(x, y, z) && world.getBlockMetadata(x, y, z) == this.meta) {
            return true;
        }
        return false;
    }
}
