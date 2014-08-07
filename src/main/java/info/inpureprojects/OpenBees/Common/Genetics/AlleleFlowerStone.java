package info.inpureprojects.OpenBees.Common.Genetics;

import cpw.mods.fml.common.registry.GameRegistry;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.AlleleFlower;
import info.inpureprojects.OpenBees.API.modInfo;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

/**
 * Created by den on 8/6/2014.
 */
public class AlleleFlowerStone extends AlleleFlower {

    private ArrayList<BlockIdentifier> validBlocks = new ArrayList();

    public AlleleFlowerStone() {
        super(modInfo.modid + "|FlowerStone", null, 0);
    }

    @Override
    public boolean isValid(World world, int x, int y, int z, ItemStack bee) {
        for (ItemStack i : OreDictionary.getOres("stone")) {
            BlockIdentifier b = new BlockIdentifier(GameRegistry.findUniqueIdentifierFor(Block.getBlockFromItem(i.getItem())).toString(), i.getItemDamage());
            if (validBlocks.contains(b)) {
                return true;
            }
        }
        return false;
    }

    public static class BlockIdentifier {

        private String id;
        private int meta;

        public BlockIdentifier(String id, int meta) {
            this.id = id;
            this.meta = meta;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BlockIdentifier)) return false;

            BlockIdentifier that = (BlockIdentifier) o;

            if (meta != that.meta) return false;
            if (!id.equals(that.id)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = id.hashCode();
            result = 31 * result + meta;
            return result;
        }
    }
}
