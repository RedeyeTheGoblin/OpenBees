package OpenBees.genetics.alleleHandlers;

import OpenBees.genetics.Allele;
import OpenBees.genetics.alleleHandlers.climateHandlers.climateDefinitions.climateDefinition;

public class alleleClimateHandler extends Allele {

    // This can require a specific biome or a temperature like hot or cold.
    // OpenBees registers one of these labeled TEMPERATE that counts as between hot and cold.
    private climateDefinition requiredClimate;

    public alleleClimateHandler(String tag, climateDefinition requiredClimate) {
        super(tag);
        this.requiredClimate = requiredClimate;
    }

    public climateDefinition getRequiredClimate() {
        return requiredClimate;
    }
}
