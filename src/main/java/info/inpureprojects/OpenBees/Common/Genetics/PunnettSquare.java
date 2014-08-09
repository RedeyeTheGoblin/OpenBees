package info.inpureprojects.OpenBees.Common.Genetics;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import info.inpureprojects.OpenBees.API.Common.Bees.IBee;

import java.util.*;

/**
 * Created by den on 8/9/2014.
 */
public class PunnettSquare {

    private List<Object[]> potential = new ArrayList<Object[]>();

    public PunnettSquare(Object[] queen, Object[] mate) {
        potential.add(new Object[]{queen[0], mate[0]});
        potential.add(new Object[]{queen[1], mate[1]});
        potential.add(new Object[]{queen[0], mate[1]});
        potential.add(new Object[]{queen[1], mate[0]});
    }

    public static Map<Allele.AlleleTypes, PunnettSquare> getGeneticPotentials(IBee queen) {
        HashMap<Allele.AlleleTypes, PunnettSquare> map = new HashMap();
        for (Allele.AlleleTypes t : Allele.AlleleTypes.values()) {
            map.put(t, new PunnettSquare(new Allele[]{queen.getDominantGenome().getRawGenome().get(t), queen.getRecessiveGenome().getRawGenome().get(t)}, new Allele[]{queen.getMate().getDominantGenome().getRawGenome().get(t), queen.getMate().getRecessiveGenome().getRawGenome().get(t)}));
        }
        return Collections.unmodifiableMap(map);
    }

    public List<Object[]> getPotential() {
        return potential;
    }
}
