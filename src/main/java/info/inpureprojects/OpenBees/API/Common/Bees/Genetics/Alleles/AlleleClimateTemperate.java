package info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles;

import info.inpureprojects.OpenBees.API.Common.Bees.Climate.ClimateDefinitionTemperate;

/**
 * Created by den on 8/9/2014.
 */
public class AlleleClimateTemperate extends AlleleClimate {

    public AlleleClimateTemperate(String tag) {
        super(tag, new ClimateDefinitionTemperate("TEMPERATE"));
    }
}
