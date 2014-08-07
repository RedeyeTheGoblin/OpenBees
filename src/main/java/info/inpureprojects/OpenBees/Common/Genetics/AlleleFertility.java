package info.inpureprojects.OpenBees.Common.Genetics;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleInt;
import info.inpureprojects.OpenBees.API.modInfo;

/**
 * Created by den on 8/6/2014.
 */
public class AlleleFertility extends AlleleInt {

    public AlleleFertility(FertilityMods m) {
        super(modInfo.modid + "|Fertility" + m.toString(), m.getMult());
    }

    public static enum FertilityMods {

        LOW,
        NORMAL,
        HIGH;

        public int getMult() {
            return this.ordinal() + 1;
        }

    }
}
