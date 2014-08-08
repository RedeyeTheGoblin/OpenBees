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

    public boolean isEqual(Block b, int meta) {
        return b == this.block && this.meta == meta;
    }

}
