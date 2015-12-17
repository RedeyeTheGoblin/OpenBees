package OpenBees.genetics;

import net.minecraft.nbt.NBTTagCompound;

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
