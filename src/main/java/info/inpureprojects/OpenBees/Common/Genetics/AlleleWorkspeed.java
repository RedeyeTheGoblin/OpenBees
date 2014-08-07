package info.inpureprojects.OpenBees.Common.Genetics;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleInt;
import info.inpureprojects.OpenBees.API.modInfo;

/**
 * Created by den on 8/6/2014.
 */
public class AlleleWorkspeed extends AlleleInt {

    public AlleleWorkspeed(Speeds speed) {
        super(modInfo.modid + "|Workspeed" + speed.toString(), speed.getMult());
    }

    public static enum Speeds {

        SLOW,
        NORMAL,
        FAST,
        FASTEST;

        public int getMult() {
            return this.ordinal() + 1;
        }

    }
}
