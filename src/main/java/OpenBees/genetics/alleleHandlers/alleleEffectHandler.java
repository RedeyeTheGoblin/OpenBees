package OpenBees.genetics.alleleHandlers;

import OpenBees.genetics.Allele;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class alleleEffectHandler extends Allele {

    public alleleEffectHandler(String tag)
    {
        super(tag);
    }

    public abstract void doEffect(World world, int x, int y, int z, ItemStack bee);
}
