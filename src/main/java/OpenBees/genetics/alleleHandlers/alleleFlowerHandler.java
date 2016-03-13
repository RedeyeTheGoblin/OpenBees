package OpenBees.genetics.alleleHandlers;

import OpenBees.genetics.Allele;
import OpenBees.genetics.IBee;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class alleleFlowerHandler extends Allele {

    private Block requiredBlock;
    private int meta;

    public alleleFlowerHandler(String tag, Block requiredBlock, int meta) {
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
