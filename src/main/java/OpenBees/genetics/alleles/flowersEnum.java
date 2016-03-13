package OpenBees.genetics.alleles;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public enum flowersEnum {

    NONE(null, 0),
    YELLOW(Blocks.yellow_flower, 0),
    RED(Blocks.red_flower, 0),
    CACTUS(Blocks.cactus, 0),
    BROWNMUSHROOM(Blocks.brown_mushroom, 0),
    REDMUSHROOM(Blocks.red_mushroom, 0),
    VINES(Blocks.vine, 0);

    private Block block;
    private int meta;

    flowersEnum(Block block, int meta) {
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
