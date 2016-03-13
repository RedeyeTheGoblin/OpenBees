package OpenBees.genetics.alleleHandlers.climateHandlers.climateDefinitions;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class climateDefinition {

    private String tag;
    private BiomeDictionary.Type type;

    public climateDefinition(String tag, BiomeDictionary.Type type) {
        this.tag = tag;
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public boolean isBiomeCompatible(BiomeGenBase biome) {
        return BiomeDictionary.isBiomeOfType(biome, this.type);
    }

    public BiomeDictionary.Type getType() {
        return type;
    }
}
