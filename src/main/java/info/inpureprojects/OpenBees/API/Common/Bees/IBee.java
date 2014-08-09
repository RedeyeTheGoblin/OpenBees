package info.inpureprojects.OpenBees.API.Common.Bees;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.IBeeGenome;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by den on 8/6/2014.
 */
public interface IBee {

    public IBeeGenome getDominantGenome();

    public IBeeGenome getRecessiveGenome();

    public NBTTagCompound getNBT();

    public IBee getMate();

    public void setMate(IBee bee);

    public boolean isHybrid();

    public void setHybrid();

    public int getLifeTicks();

    public void setLifeTicks(int ticks);

}
