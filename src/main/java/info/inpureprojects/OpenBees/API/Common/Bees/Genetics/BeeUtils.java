package info.inpureprojects.OpenBees.API.Common.Bees.Genetics;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleInt;
import info.inpureprojects.OpenBees.API.Common.Bees.IBee;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.TileApiary;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Map;

/**
 * Created by den on 8/9/2014.
 */
public class BeeUtils {

    public static final int GENERATE_NEW_LIFE_FLAG = -42;
    public static BeeUtils instance = new BeeUtils();

    public NBTTagCompound generateGenome(IBee bee) {
        return this.generateGenome(bee.getDominantGenome().getSpecies(), bee.getRecessiveGenome().getSpecies(), bee.getDominantGenome().getRawGenome(), bee.getRecessiveGenome().getRawGenome(), bee.isHybrid(), bee.getLifeTicks(), bee.getMate());
    }

    public NBTTagCompound generateGenome(ISpecies pSpecies, ISpecies sSpecies, Map<Allele.AlleleTypes, Allele> primary, Map<Allele.AlleleTypes, Allele> secondary, boolean hybrid, int lifeTicks, IBee mate) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("Primaryspecies", pSpecies.getTag());
        tag.setString("Secondaryspecies", sSpecies.getTag());
        NBTTagCompound genome = new NBTTagCompound();
        NBTTagCompound genome2 = new NBTTagCompound();
        for (Allele.AlleleTypes t : Allele.AlleleTypes.values()) {
            genome.setString(t.toString(), primary.get(t).getTag());
            genome2.setString(t.toString(), secondary.get(t).getTag());
        }
        tag.setTag("primaryGenome", genome);
        tag.setTag("secondaryGenome", genome2);
        tag.setBoolean("hybrid", hybrid);
        if (mate != null) {
            tag.setTag("mate", mate.getNBT());
        }
        if (lifeTicks == GENERATE_NEW_LIFE_FLAG) {
            tag.setInteger("life", TileApiary.baseLifeTicks * ((AlleleInt) primary.get(Allele.AlleleTypes.LIFESPAN)).getNumber());
        } else {
            tag.setInteger("life", lifeTicks);
        }
        return tag;
    }

}
