package info.inpureprojects.OpenBees.Common.Managers;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.BeeProduct;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.BeeUtils;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.ISpecies;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.List;

/**
 * Created by den on 8/6/2014.
 */
public class SpeciesImpl implements ISpecies {

    private String tag;
    private String unloc;
    private int bodyColor;
    private int outlineColor;
    private HashMap<Allele.AlleleTypes, Allele> alleles;
    private List<BeeProduct> products;

    public SpeciesImpl(String tag, String unloc, int bodyColor, int outlineColor, HashMap<Allele.AlleleTypes, Allele> alleles, List<BeeProduct> products) {
        this.tag = tag;
        this.unloc = unloc;
        this.bodyColor = bodyColor;
        this.outlineColor = outlineColor;
        this.alleles = alleles;
        this.products = products;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public String getUnlocalizedName() {
        return this.unloc;
    }

    @Override
    public int getBodyColor() {
        return this.bodyColor;
    }

    @Override
    public int getOutlineColor() {
        return this.outlineColor;
    }

    @Override
    public HashMap<Allele.AlleleTypes, Allele> getGenome() {
        return this.alleles;
    }

    @Override
    public NBTTagCompound generateGenericGenome() {
        return BeeUtils.instance.generateGenome(this, this, this.getGenome(), this.getGenome(), false, BeeUtils.GENERATE_NEW_LIFE_FLAG, null);
    }

    @Override
    public List<BeeProduct> getPotentialProducts() {
        return this.products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpeciesImpl)) return false;

        SpeciesImpl species = (SpeciesImpl) o;

        if (bodyColor != species.bodyColor) return false;
        if (outlineColor != species.outlineColor) return false;
        if (!alleles.equals(species.alleles)) return false;
        if (!products.equals(species.products)) return false;
        if (!tag.equals(species.tag)) return false;
        if (!unloc.equals(species.unloc)) return false;

        return true;
    }

    @Override
    public String toString() {
        return "SpeciesImpl{" +
                "tag='" + tag + '\'' +
                '}';
    }
}
