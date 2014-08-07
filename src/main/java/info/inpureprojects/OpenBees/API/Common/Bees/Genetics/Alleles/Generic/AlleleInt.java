package info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;

/**
 * Created by den on 8/6/2014.
 */
public class AlleleInt extends Allele {

    private int number;

    public AlleleInt(String tag, int number) {
        super(tag);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
