package OpenBees.genetics.alleleHandlers.climateHandlers;

import OpenBees.genetics.alleleHandlers.alleleClimateHandler;
import OpenBees.genetics.alleleHandlers.climateHandlers.climateDefinitions.climateDefinitionTemperate;

public class alleleClimateTemperate extends alleleClimateHandler {

    public alleleClimateTemperate(String tag)
    {
        super(tag, new climateDefinitionTemperate("TEMPERATE"));
    }
}
