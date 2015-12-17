package OpenBees.handler;

import OpenBees.genetics.Allele;

import java.util.Map;

public interface IalleleHandler {

    public void registerAllele(Allele allele);

    public Allele getAlleleByTag(String tag);

    public Map<String, Allele> getAlleleMap();

}
