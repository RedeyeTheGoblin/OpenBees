package info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles;

import info.inpureprojects.OpenBees.API.Common.Bees.Climate.ClimateDefinition;

/**
 * Created by den on 8/6/2014.
 */
public class AlleleClimate extends Allele {

    private ClimateDefinition requiredClimate;

    public AlleleClimate(String tag, ClimateDefinition requiredClimate) {
        super(tag);
        this.requiredClimate = requiredClimate;
    }

    public ClimateDefinition getRequiredClimate() {
        return requiredClimate;
    }
}
