package OpenBees.genetics;

import java.util.*;

public class punnettSquare {

    private List<Object[]> potential = new ArrayList<Object[]>();

    public punnettSquare(Object[] queen, Object[] mate) {
        // LEFT-TOP QUAD
        potential.add(new Object[]{mate[0], queen[0]});
        potential.add(new Object[]{queen[0], mate[0]});

        // RIGHT-TOP QUAD
        potential.add(new Object[]{queen[1], mate[0]});
        potential.add(new Object[]{mate[1], queen[0]});

        // LEFT-BOTTOM QUAD
        potential.add(new Object[]{queen[0], mate[1]});
        potential.add(new Object[]{mate[0], queen[1]});

        // RIGHT-BOTTOM QUAD
        potential.add(new Object[]{mate[1], queen[1]});
        potential.add(new Object[]{queen[1], mate[1]});
    }

    public static Map<Allele.AlleleTypes, punnettSquare> getGeneticPotentials(IBee queen) {
        HashMap<Allele.AlleleTypes, punnettSquare> map = new HashMap();

        for(Allele.AlleleTypes types : Allele.AlleleTypes.values()) {
            map.put(types, new punnettSquare(new Allele[]{queen.getDominantGenome().getRawGenome().get(types), queen.getRecessiveGenome().getRawGenome().get(types)}, new Allele[]{queen.getMate().getDominantGenome().getRawGenome().get(types), queen.getMate().getRecessiveGenome().getRawGenome().get(types)}));
        }
        return Collections.unmodifiableMap(map);
    }

    public List<Object[]> getPotential() {
        return potential;
    }
}
