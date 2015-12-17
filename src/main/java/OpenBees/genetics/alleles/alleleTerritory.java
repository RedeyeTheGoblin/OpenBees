package OpenBees.genetics.alleles;

import OpenBees.genetics.alleleHandlers.alleleIntHandler;

public class alleleTerritory extends alleleIntHandler {

    public alleleTerritory (territoryMods mods)
    {
        super("Territory." + mods.toString(), mods.getMult());
    }

    public static enum territoryMods
    {

        SMALL,
        NORMAL,
        LARGE;

        public int getMult()
        {
            return this.ordinal() + 1;
        }
    }
}
