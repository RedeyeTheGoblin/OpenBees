package info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by den on 8/6/2014.
 */
public abstract class AlleleEffect extends Allele {

    public AlleleEffect(String tag) {
        super(tag);
    }

    public abstract void doEffect(World world, int x, int y, int z, ItemStack bee);
}
