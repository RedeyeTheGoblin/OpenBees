package info.inpureprojects.OpenBees.Common.Managers;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.IBeeGenome;
import info.inpureprojects.OpenBees.API.Common.Bees.IBee;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;

/**
 * Created by den on 8/6/2014.
 */
public class BeeImpl implements IBee {

    private IBeeGenome genome;
    private NBTTagCompound tags;

    public BeeImpl(NBTTagCompound genome) {
        this.tags = genome;
        HashMap<Allele.AlleleTypes, Allele> map = new HashMap();
        for (Allele.AlleleTypes t : Allele.AlleleTypes.values()) {
            map.put(t, OpenBeesAPI.getAPI().getCommonAPI().beeManager.getAlleleManager().getAlleleByTag(genome.getString(t.toString())));
        }
        this.genome = new GenomeImpl(OpenBeesAPI.getAPI().getCommonAPI().beeManager.getSpeciesByTag(genome.getString("species")), map);
    }

    @Override
    public IBeeGenome getGenome() {
        return this.genome;
    }

    @Override
    public NBTTagCompound getNBT() {
        return (NBTTagCompound) this.tags.copy();
    }
}
