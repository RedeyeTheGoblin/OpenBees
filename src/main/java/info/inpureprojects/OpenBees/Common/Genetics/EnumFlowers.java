package info.inpureprojects.OpenBees.Common.Genetics;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

/**
 * Created by den on 8/6/2014.
 */
public enum EnumFlowers {

    NONE(null, 0),
    YELLOW(Blocks.yellow_flower, 0),
    RED(Blocks.red_flower, 0);

    private Block block;
    private int meta;

    EnumFlowers(Block block, int meta) {
        this.block = block;
        this.meta = meta;
    }

    public Block getBlock() {
        return block;
    }

    public int getMeta() {
        return meta;
    }
}
