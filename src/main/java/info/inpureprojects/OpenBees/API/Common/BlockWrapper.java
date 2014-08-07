package info.inpureprojects.OpenBees.API.Common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/6/2014.
 */
public class BlockWrapper {

    private Block block;
    private int meta;

    public BlockWrapper(Block block, int meta) {
        this.block = block;
        this.meta = meta;
        GameRegistry.registerBlock(block, block.getUnlocalizedName());
    }

    public ItemStack getStack(int quantity) {
        return new ItemStack(block, quantity, meta);
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockWrapper)) return false;

        BlockWrapper that = (BlockWrapper) o;

        if (meta != that.meta) return false;
        if (!block.equals(that.block)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = block.hashCode();
        result = 31 * result + meta;
        return result;
    }
}
