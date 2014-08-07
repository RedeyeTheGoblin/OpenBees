package info.inpureprojects.OpenBees.Common.Genetics;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleInt;
import info.inpureprojects.OpenBees.API.modInfo;

/**
 * Created by den on 8/6/2014.
 */
public class AllelePollination extends AlleleInt {

    public AllelePollination(PollinationMods p) {
        super(modInfo.modid + "|Pollination" + p.toString(), p.getMult());
    }

    public static enum PollinationMods {

        SLOW,
        NORMAL,
        FAST;

        public int getMult() {
            return this.ordinal() + 1;
        }

    }
}
