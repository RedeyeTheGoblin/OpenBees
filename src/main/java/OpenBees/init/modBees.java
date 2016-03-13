package OpenBees.init;

import OpenBees.OpenBees;
import OpenBees.enums.speciesEnum;
import OpenBees.genetics.Allele;
import OpenBees.genetics.baseSpecies;
import OpenBees.utility.logHelper;

import java.util.HashMap;

public class modBees {

    public static void init() {

        for (speciesEnum.Species species : speciesEnum.Species.values()) {

            HashMap<Allele.AlleleTypes, Allele> map = new HashMap();
            for (Allele.AlleleTypes types : Allele.AlleleTypes.values()) {

                Allele allele = OpenBees.coreBeeHandler.getAlleleHandler().getAlleleByTag(species.getGenome()[types.ordinal()]);
                map.put(types, allele);

            }
            OpenBees.coreBeeHandler.registerSpecies(new baseSpecies(species.toString(), species.getUnloc() ,species.getbodyColour(), species.getoutlineColour(), map, species.getProducts()));
            OpenBees.coreBeeHandler.registerBeeforHive(OpenBees.coreBeeHandler.getSpeciesByTag(species.toString()), species.getSpawnIn());
        }
    }

    public static  void registerMutations() {
        OpenBees.coreBeeHandler.registerMutation("FOREST", "MEADOWS", "COMMON", 0.60f);
        OpenBees.coreBeeHandler.registerMutation("FOREST", "MODEST", "COMMON", 0.60f);
        OpenBees.coreBeeHandler.registerMutation("FOREST", "MARSHY", "COMMON", 0.60f);
        OpenBees.coreBeeHandler.registerMutation("FOREST", "WINTRY", "COMMON", 0.60f);
        OpenBees.coreBeeHandler.registerMutation("FOREST", "TROPICAL", "COMMON", 0.60f);

        OpenBees.coreBeeHandler.registerMutation("MEADOWS", "MODEST", "COMMON", 0.60f);
        OpenBees.coreBeeHandler.registerMutation("MEADOWS", "MARSHY", "COMMON", 0.60f);
        OpenBees.coreBeeHandler.registerMutation("MEADOWS", "WINTRY", "COMMON", 0.60f);
        OpenBees.coreBeeHandler.registerMutation("MEADOWS", "TROPICAL", "COMMON", 0.60f);

        OpenBees.coreBeeHandler.registerMutation("MARSHY", "WINTRY", "COMMON", 0.60f);
        OpenBees.coreBeeHandler.registerMutation("MARSHY", "TROPICAL", "COMMON", 0.60f);

        OpenBees.coreBeeHandler.registerMutation("WINTRY", "TROPICAL", "COMMON", 0.60f);
    }
}
