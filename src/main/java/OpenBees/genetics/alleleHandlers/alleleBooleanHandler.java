package OpenBees.genetics.alleleHandlers;

import OpenBees.genetics.Allele;

public class alleleBooleanHandler extends Allele
{

    private boolean bool;

    public alleleBooleanHandler(String tag, boolean bool) {
        super(tag);
        this.bool = bool;
    }

    public boolean isBool() {
        return bool;
    }
}
