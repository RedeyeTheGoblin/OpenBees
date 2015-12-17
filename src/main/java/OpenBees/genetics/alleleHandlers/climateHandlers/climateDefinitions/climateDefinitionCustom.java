package OpenBees.genetics.alleleHandlers.climateHandlers.climateDefinitions;


import net.minecraft.world.biome.BiomeGenBase;

public abstract class climateDefinitionCustom extends climateDefinition {

    public climateDefinitionCustom(String tag)
    {
        super(tag, null);
    }

    @Override
    public abstract boolean isBiomeCompatible(BiomeGenBase biome);
}
