package OpenBees.genetics.alleles;

import OpenBees.genetics.alleleHandlers.alleleIntHandler;

public class alleleFertility extends alleleIntHandler {

    public alleleFertility(fertilityMods mods)
    {
        super("Fertility." + mods.toString(), mods.getMult());
    }

    public static enum fertilityMods
    {
        LOW,
        NORMAL,
        HIGH;

        public int getMult()
        {
            return this.ordinal() + 1;
        }
    }
}
