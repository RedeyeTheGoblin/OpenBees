package OpenBees.genetics.alleles;

import OpenBees.genetics.alleleHandlers.alleleIntHandler;

public class alleleWorkspeed extends alleleIntHandler {

    public alleleWorkspeed(Speeds speed) {
        super("Workspeed." + speed.toString(), speed.getMult());
    }

    public static enum Speeds {

        GLACIAL,
        SLOW,
        NORMAL,
        FAST,
        FASTER,
        HYPER,
        LUDICROUS;

        public int getMult()
        {
            return this.ordinal() + 1;
        }

    }
}
