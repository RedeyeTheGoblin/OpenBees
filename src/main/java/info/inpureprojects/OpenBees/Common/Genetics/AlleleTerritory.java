package info.inpureprojects.OpenBees.Common.Genetics;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleInt;
import info.inpureprojects.OpenBees.API.modInfo;

/**
 * Created by den on 8/6/2014.
 */
public class AlleleTerritory extends AlleleInt {

    public AlleleTerritory(TerritoryMods mod) {
        super(modInfo.modid + "|Territory" + mod.toString(), mod.getMult());
    }

    public static enum TerritoryMods {

        NORMAL,
        LARGE;

        public int getMult() {
            return this.ordinal() + 1;
        }

    }
}
