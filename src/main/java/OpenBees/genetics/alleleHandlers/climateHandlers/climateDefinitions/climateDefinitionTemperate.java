package OpenBees.genetics.alleleHandlers.climateHandlers.climateDefinitions;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class climateDefinitionTemperate extends climateDefinitionCustom {

    public climateDefinitionTemperate(String tag)
    {
        super(tag);
    }

    @Override
    public boolean isBiomeCompatible(BiomeGenBase biome) {
        if (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.COLD) && !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.HOT)) {
            return true;
        }
        return false;
    }
}
