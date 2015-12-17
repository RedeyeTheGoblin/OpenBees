package OpenBees.genetics;

import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.List;

public interface ISpecies {

    public String getTag();

    public String getUnlocalizedName();

    public int getBodyColour();

    public int getOutlineColour();

    public HashMap<Allele.AlleleTypes, Allele> getGenome();

    public NBTTagCompound generateGenericGenome();

    public List<beeProduct> getPotentialProducts();

}
