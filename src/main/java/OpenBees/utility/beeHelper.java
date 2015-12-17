package OpenBees.utility;

import OpenBees.OpenBees;
import OpenBees.block.tileEntities.tileApiary;
import OpenBees.genetics.Allele;
import OpenBees.genetics.IBee;
import OpenBees.genetics.ISpecies;
import OpenBees.init.modItems;
import OpenBees.genetics.alleleHandlers.alleleIntHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Map;

public class beeHelper {

    public static final int generateNewLifeFlag = -42;
    public static final int defaultLifeTicks = 45;
    public static beeHelper instance = new beeHelper();

    public boolean areBeesIdentical(IBee bee1, IBee bee2)
    {
        return bee1.getDominantGenome().isIdentical(bee2.getDominantGenome()) &&
                bee1.getRecessiveGenome().isIdentical(bee2.getRecessiveGenome());
    }

    public boolean isQueen(ItemStack stack)
    {
        if (stack == null)
        {
            return false;
        }
        return stack.getItem() == OpenBees.items.queen.getItem() ;
    }

    public boolean isPrincess(ItemStack stack)
    {
        if (stack == null)
        {
            return false;
        }
        return stack.getItem() == OpenBees.items.princess.getItem();
    }

    public boolean isDrone(ItemStack stack)
    {
        if (stack == null)
        {
            return false;
        }
        return stack.getItem() == OpenBees.items.drone.getItem();
    }

    public NBTTagCompound generateGenome(IBee bee)
    {
        return this.generateGenome(bee.getDominantGenome().getSpecies(), bee.getRecessiveGenome().getSpecies(), bee.getDominantGenome().getRawGenome(), bee.getRecessiveGenome().getRawGenome(), bee.isHybrid(), bee.getLifeTicks(), bee.getMate());

    }

    public NBTTagCompound generateGenome(ISpecies primarySpecies, ISpecies secondarySpecies, Map<Allele.AlleleTypes, Allele> primaryAlleles, Map<Allele.AlleleTypes, Allele> secondaryAlleles, boolean hybrid, int lifeTicks, IBee mate)
    {
        NBTTagCompound tagNBT = new NBTTagCompound();
        tagNBT.setString("PrimarySpecies", primarySpecies.getTag());
        tagNBT.setString("SecondarySpecies", secondarySpecies.getTag());
        NBTTagCompound primaryGenome = new NBTTagCompound();
        NBTTagCompound secondaryGenome = new NBTTagCompound();

        for (Allele.AlleleTypes types : Allele.AlleleTypes.values())
        {
            primaryGenome.setString(types.toString(), primaryAlleles.get(types).getTag());
            secondaryGenome.setString(types.toString(), secondaryAlleles.get(types).getTag());
        }

        tagNBT.setTag("PrimaryGenome", primaryGenome);
        tagNBT.setTag("SecondaryGenome", secondaryGenome);
        tagNBT.setBoolean("hybrid", hybrid);

        if (mate != null)
        {
            tagNBT.setTag("mate", mate.getNBT());
        }

        if (lifeTicks == generateNewLifeFlag)
        {
            tagNBT.setInteger("life", tileApiary.baseLifeTicks * ((alleleIntHandler) primaryAlleles.get(Allele.AlleleTypes.LIFESPAN)).getNumber());
        } else
        {
            tagNBT.setInteger("life", (defaultLifeTicks * ((alleleIntHandler) primaryAlleles.get(Allele.AlleleTypes.LIFESPAN)).getNumber()));
        }
        return tagNBT;

    }
}
