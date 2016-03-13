package OpenBees.genetics.alleles;

import OpenBees.genetics.alleleHandlers.alleleIntHandler;

public class alleleLifespan  extends alleleIntHandler {

    public alleleLifespan(Lifespans lifespan)
    {
        super("Lifespan." + lifespan.toString(), lifespan.getMult());
    }

    public static enum Lifespans {
        SHORT,
        NORMAL,
        LONG,
        LONGER,
        LONGEST,
        AEONS;

        public int getMult()
        {
            return this.ordinal() + 1;
        }
    }
}
