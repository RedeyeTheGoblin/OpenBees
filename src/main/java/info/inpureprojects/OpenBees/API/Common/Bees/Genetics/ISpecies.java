package info.inpureprojects.OpenBees.API.Common.Bees.Genetics;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.List;

/**
 * Created by den on 8/6/2014.
 */
public interface ISpecies {

    public String getTag();

    public String getUnlocalizedName();

    public int getBodyColor();

    public HashMap<Allele.AlleleTypes, Allele> getGenome();

    public NBTTagCompound generateGenericGenome();

    public List<BeeProduct> getPotentialProducts();

}
