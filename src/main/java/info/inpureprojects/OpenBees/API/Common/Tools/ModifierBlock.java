package info.inpureprojects.OpenBees.API.Common.Tools;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

import java.util.List;

/**
 * Created by den on 8/10/2014.
 */
public abstract class ModifierBlock implements IFrameItem {

    private String tag;
    private Block block;
    private int meta;

    public ModifierBlock(Block block, int meta) {
        this.block = block;
        this.meta = meta;
        if (block != null){
            this.tag = GameRegistry.findUniqueIdentifierFor(block).modId + "|" + GameRegistry.findUniqueIdentifierFor(block).name + String.valueOf(meta);
        }
    }

    public Block getBlock() {
        return block;
    }

    public String getTag() {
        return tag;
    }

    public int getMeta() {
        return meta;
    }

}
