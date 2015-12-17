package OpenBees.block;


import OpenBees.item.interfaces.IFrameItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public abstract class modifierBlock implements IFrameItem {

    private String tag;
    private Block block;
    private int meta;

    public modifierBlock(Block block, int meta)
    {
        this.block = block;
        this.meta = meta;
        if (block != null)
        {
            this.tag = GameRegistry.findUniqueIdentifierFor(block).modId + "." + GameRegistry.findUniqueIdentifierFor(block).name + ":" + String.valueOf(meta);
        }
    }

    public Block getBlock()
    {
        return block;
    }

    public String getTag()
    {
        return tag;
    }

    public int getMeta()
    {
        return meta;
    }
}
