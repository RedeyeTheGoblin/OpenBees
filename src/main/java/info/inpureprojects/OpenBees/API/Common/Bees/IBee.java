package info.inpureprojects.OpenBees.API.Common.Bees;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.IBeeGenome;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by den on 8/6/2014.
 */
public interface IBee {

    public IBeeGenome getGenome();

    public NBTTagCompound getNBT();

}
