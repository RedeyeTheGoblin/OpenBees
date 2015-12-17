package OpenBees.handler;

import OpenBees.genetics.Allele;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class alleleHandler implements IalleleHandler {

    private HashMap<String, Allele> alleles = new HashMap();

    @Override
    public void registerAllele(Allele allele)
    {
        alleles.put(allele.getTag(), allele);
    }

    @Override
    public Allele getAlleleByTag(String tag)
    {
        return alleles.get(tag);
    }

    @Override
    public Map<String, Allele> getAlleleMap()
    {
        return Collections.unmodifiableMap(this.alleles);
    }
}
