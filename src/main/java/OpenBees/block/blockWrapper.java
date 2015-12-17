package OpenBees.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class blockWrapper {

    private Block block;
    private int meta;

    public blockWrapper(Block block, int meta)
    {
        this.block = block;
        this.meta = meta;
    }

    public ItemStack getStack(int quantity)
    {
        return new ItemStack(block, quantity, meta);
    }

    public Block getBlock()
    {
        return block;
    }

    public boolean isEqual(Block b, int meta)
    {
        return b == this.block && this.meta == meta;
    }
}
