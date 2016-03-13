package OpenBees.genetics;

import OpenBees.OpenBees;
import OpenBees.utility.beeHelper;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.List;

public class baseSpecies implements ISpecies {

    private String tag;
    private String unloc;
    private int bodyColour;
    private int outlineColour;
    private HashMap<Allele.AlleleTypes, Allele> alleles;
    private List<beeProduct> products;

    public baseSpecies(String tag, String unloc, int bodyColour, int outlineColour, HashMap<Allele.AlleleTypes, Allele> alleles, List<beeProduct> products) {
        this.tag = tag;
        this.unloc = unloc;
        this.bodyColour = bodyColour;
        this.outlineColour = outlineColour;
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
    public int getBodyColour() {
        return this.bodyColour;
    }

    @Override
    public int getOutlineColour() {
        return this.outlineColour;
    }

    @Override
    public HashMap<Allele.AlleleTypes, Allele> getGenome() {
        return this.alleles;
    }

    @Override
    public NBTTagCompound generateGenericGenome() {
        return OpenBees.coreBeeHelper.generateGenome(this, this, this.getGenome(), this.getGenome(), false, beeHelper.generateNewLifeFlag, null);
    }

    @Override
    public List<beeProduct> getPotentialProducts() {
        return this.products;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(!(obj instanceof baseSpecies)) return true;

        baseSpecies species = (baseSpecies) obj;

        if (bodyColour != species.bodyColour) return false;
        if (outlineColour != species.outlineColour) return false;
        if (!alleles.equals(species.alleles)) return false;
        if (!products.equals(species.products)) return false;
        if (!tag.equals(species.tag)) return false;
        if (!unloc.equals(species.unloc)) return false;

        return true;
    }

    @Override
    public String toString() {
        return "baseSpecies{" + "tag'=" + tag + '\'' + '}';
    }

}
