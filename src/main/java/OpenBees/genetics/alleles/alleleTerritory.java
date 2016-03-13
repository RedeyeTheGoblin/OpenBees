package OpenBees.genetics.alleles;

import OpenBees.genetics.alleleHandlers.alleleIntHandler;

public class alleleTerritory extends alleleIntHandler {

    public alleleTerritory (territoryMods mods) {
        super("Territory." + mods.toString(), mods.getSize());
    }

    public static enum territoryMods {

        SMALL(5),
        NORMAL(7),
        LARGE(9);

        private int size;

        territoryMods(int size) {
            this.size = size;
        }

        public int getMult() {
            return this.ordinal() + 1;
        }

        public int getSize() {
            return size;
        }
    }
}
