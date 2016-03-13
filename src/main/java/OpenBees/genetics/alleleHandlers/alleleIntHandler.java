package OpenBees.genetics.alleleHandlers;

import OpenBees.genetics.Allele;

public class alleleIntHandler extends Allele {

    private int number;

    public alleleIntHandler(String tag, int number) {
        super(tag);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
