package OpenBees.genetics;

import OpenBees.OpenBees;
import OpenBees.handler.beeHandler;
import OpenBees.utility.beeHelper;
import OpenBees.utility.logHelper;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;

public class baseBee implements IBee {

    private IBeeGenome dominant;
    private IBeeGenome recessive;
    private IBee mate;
    private boolean isHybrid = false;
    private int lifeTicks = 45;

    public baseBee(NBTTagCompound genome) {
        HashMap<Allele.AlleleTypes, Allele> primaryAlleleMap = new HashMap();
        HashMap<Allele.AlleleTypes, Allele> secondaryAlleleMap = new HashMap();
        for (Allele.AlleleTypes types : Allele.AlleleTypes.values()) {
            primaryAlleleMap.put(types, OpenBees.coreBeeHandler.getAlleleHandler().getAlleleByTag(genome.getCompoundTag("PrimaryGenome").getString(types.toString())));
            secondaryAlleleMap.put(types, OpenBees.coreBeeHandler.getAlleleHandler().getAlleleByTag(genome.getCompoundTag("SecondaryGenome").getString(types.toString())));
        }

        this.dominant = new baseGenome(OpenBees.coreBeeHandler.getSpeciesByTag(genome.getString("PrimarySpecies")), primaryAlleleMap);
        this.recessive = new baseGenome(OpenBees.coreBeeHandler.getSpeciesByTag(genome.getString("SecondarySpecies")), secondaryAlleleMap);

        if (genome.hasKey("mate")) {
            this.mate = new baseBee(genome.getCompoundTag("mate"));
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
        return beeHelper.instance.generateGenome(this);
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
