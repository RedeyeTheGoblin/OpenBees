package OpenBees.genetics.alleles;

import OpenBees.genetics.alleleHandlers.alleleIntHandler;

public class allelePollination extends alleleIntHandler {

    public allelePollination(PollinationMods mods) {
        super("Pollination." + mods.toString(), mods.getMult());
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
