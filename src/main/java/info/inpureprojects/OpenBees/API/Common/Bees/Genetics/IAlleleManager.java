package info.inpureprojects.OpenBees.API.Common.Bees.Genetics;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;

import java.util.Map;

/**
 * Created by den on 8/6/2014.
 */
public interface IAlleleManager {

    public void registerAllele(Allele allele);

    public Allele getAlleleByTag(String tag);

    public Map<String, Allele> getAlleleMap();

}
