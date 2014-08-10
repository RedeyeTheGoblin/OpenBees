package info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles;

import info.inpureprojects.OpenBees.API.Common.Bees.Climate.ClimateDefinition;

/**
 * Created by den on 8/6/2014.
 */
public class AlleleClimate extends Allele {

    // This can require a specific biome or a temperature like hot or cold.
    // OpenBees registers one of these labeled TEMPERATE that counts as between hot and cold.
    private ClimateDefinition requiredClimate;

    public AlleleClimate(String tag, ClimateDefinition requiredClimate) {
        super(tag);
        this.requiredClimate = requiredClimate;
    }

    public ClimateDefinition getRequiredClimate() {
        return requiredClimate;
    }
}
