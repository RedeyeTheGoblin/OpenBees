package info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;

/**
 * Created by den on 8/6/2014.
 */
public class AlleleBoolean extends Allele {

    private boolean bool;

    public AlleleBoolean(String tag, boolean bool) {
        super(tag);
        this.bool = bool;
    }

    public boolean isBool() {
        return bool;
    }
}
