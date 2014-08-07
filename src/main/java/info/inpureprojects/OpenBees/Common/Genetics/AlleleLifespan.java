package info.inpureprojects.OpenBees.Common.Genetics;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleInt;
import info.inpureprojects.OpenBees.API.modInfo;

/**
 * Created by den on 8/6/2014.
 */
public class AlleleLifespan extends AlleleInt {

    public AlleleLifespan(Lifespans lifespan) {
        super(modInfo.modid + "|Lifespan" + lifespan.toString(), lifespan.getMult());
    }

    public static enum Lifespans {

        SHORT,
        NORMAL,
        LONG;

        public int getMult() {
            return this.ordinal() + 1;
        }

    }
}
