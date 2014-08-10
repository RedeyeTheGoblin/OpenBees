package info.inpureprojects.OpenBees.API.Common.Bees.Climate;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

/**
 * Created by den on 8/9/2014.
 */
public class ClimateDefinitionTemperate extends ClimateDefinitionCustom {

    public ClimateDefinitionTemperate(String tag) {
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
