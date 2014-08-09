package info.inpureprojects.OpenBees.Common.Managers;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.BeeUtils;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.IBeeGenome;
import info.inpureprojects.OpenBees.API.Common.Bees.IBee;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;

/**
 * Created by den on 8/6/2014.
 */
public class BeeImpl implements IBee {

    private IBeeGenome dominant;
    private IBeeGenome recessive;
    private IBee mate;
    private boolean isHybrid = false;
    private int lifeTicks = 0;

    public BeeImpl(NBTTagCompound genome) {
        HashMap<Allele.AlleleTypes, Allele> primaryMap = new HashMap();
        HashMap<Allele.AlleleTypes, Allele> secondaryMap = new HashMap();
        for (Allele.AlleleTypes t : Allele.AlleleTypes.values()) {
            primaryMap.put(t, OpenBeesAPI.getAPI().getCommonAPI().beeManager.getAlleleManager().getAlleleByTag(genome.getCompoundTag("primaryGenome").getString(t.toString())));
            secondaryMap.put(t, OpenBeesAPI.getAPI().getCommonAPI().beeManager.getAlleleManager().getAlleleByTag(genome.getCompoundTag("secondaryGenome").getString(t.toString())));
        }
        this.dominant = new GenomeImpl(OpenBeesAPI.getAPI().getCommonAPI().beeManager.getSpeciesByTag(genome.getString("Primaryspecies")), primaryMap);
        this.recessive = new GenomeImpl(OpenBeesAPI.getAPI().getCommonAPI().beeManager.getSpeciesByTag(genome.getString("Secondaryspecies")), secondaryMap);
        if (genome.hasKey("mate")) {
            this.mate = new BeeImpl(genome.getCompoundTag("mate"));
        }
        if (genome.hasKey("hybrid")) {
            isHybrid = genome.getBoolean("hybrid");
        }
        if (genome.hasKey("life")) {
            lifeTicks = genome.getInteger("life");
        }
    }

    @Override
    public IBee getMate() {
        return this.mate;
    }

    @Override
    public void setMate(IBee bee) {
        this.mate = bee;
    }

    @Override
    public IBeeGenome getDominantGenome() {
        return this.dominant;
    }

    @Override
    public IBeeGenome getRecessiveGenome() {
        return this.recessive;
    }

    @Override
    public NBTTagCompound getNBT() {
        return BeeUtils.instance.generateGenome(this);
    }

    @Override
    public boolean isHybrid() {
        return this.isHybrid;
    }

    @Override
    public void setHybrid() {
        this.isHybrid = true;
    }

    @Override
    public int getLifeTicks() {
        return this.lifeTicks;
    }

    @Override
    public void setLifeTicks(int ticks) {
        this.lifeTicks = ticks;
    }
}
